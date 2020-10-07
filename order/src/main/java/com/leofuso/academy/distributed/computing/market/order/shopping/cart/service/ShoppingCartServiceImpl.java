package com.leofuso.academy.distributed.computing.market.order.shopping.cart.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;
import com.leofuso.academy.distributed.computing.market.order.offer.model.Offer;
import com.leofuso.academy.distributed.computing.market.order.product.stock.ProductStockService;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.RemoveStockProductItemRequest;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.RemoveStockProductRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.AddCartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.CartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.RemoveCartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource.ItemResource;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.Cart;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository.CartItemRepository;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository.CartRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@Service
class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductStockService stockService;
    private final ConversionService converter;

    ShoppingCartServiceImpl(CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            ProductStockService stockService,
                            ConversionService conversionService) {
        this.cartRepository = Objects.requireNonNull(cartRepository);
        this.cartItemRepository = Objects.requireNonNull(cartItemRepository);
        this.stockService = Objects.requireNonNull(stockService);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @Override
    public Mono<Cart> create() {
        final Cart emptyCart = Cart.empty();
        return Mono.just(cartRepository.save(emptyCart));
    }

    @Override
    public Mono<Cart> retrieve(Long id) {
        final Cart cart = cartRepository.findById(id)
                                        .orElseThrow();
        return Mono.just(cart);
    }

    @Override
    public Mono<Cart> order(Long id) {
        final Cart cart = cartRepository.findById(id)
                                        .orElseThrow();

        cart.close();

        final List<RemoveStockProductItemRequest> items =
                cartItemRepository.findAllByCartId(id)
                                  .stream()
                                  .map(cartItem -> {
                                      final Long productReference = cartItem.getProductReference();
                                      final Integer quantity = cartItem.getQuantity();

                                      return new RemoveStockProductItemRequest(
                                              productReference,
                                              quantity
                                      );

                                  })
                                  .collect(Collectors.toList());

        final RemoveStockProductRequest request = new RemoveStockProductRequest(items);

        /* If nothing explodes */
        return stockService.remove(request)
                           .map(clientResponse -> {

                               final HttpStatus httpStatus = clientResponse.statusCode();
                               if (httpStatus.isError()) {
                                   throw new RuntimeException("Something happened.");
                               }

                               return cartRepository.save(cart);
                           });
    }

    private Mono<Cart> retrieveAvailableCart(CartItemRequest request) {
        final Long cartId = request.getCartId();
        final Cart cart = cartRepository.findById(cartId)
                                        .orElseThrow();

        if (!cart.isAvailable()) {
            throw new RuntimeException("Cart is unavailable");
        }
        return Mono.just(cart);
    }

    private Mono<CartItem> retrieveCartItem(CartItemRequest request) {
        final Long cartId = request.getCartId();
        final Long offerId = request.getOfferId();

        return cartItemRepository
                .findByCartIdAndProductReference(cartId, offerId)
                .map(Mono::just)
                .orElseGet(() -> Mono.just(CartItem.ofEmpty()));
    }

    @Override
    public Mono<Cart> addItem(Mono<AddCartItemRequest> requestFlow) {
        return requestFlow
                .flatMap(request -> getPreAddItemRequestSupport(request)
                        .map(tuple -> {

                            final Cart cart = tuple.getT1();
                            final ProductStock product = tuple.getT2();

                            final CartItem item = tuple.getT3();
                            if (item.isEmpty()) {

                                final Long offerId = product.getId();

                                item.setCart(cart);
                                item.setProductReference(offerId);

                            }

                            final Integer quantity = request.getQuantity();
                            item.updateQuantity(quantity);

                            validateItemQuantity(product, item);

                            final Long additionalTotal = item.calculateSubTotal(quantity, product.getPrice());
                            cart.addToTotal(additionalTotal);

                            cartItemRepository.save(item);
                            return cartRepository.save(cart);

                        })
                );
    }

    @Override
    public Mono<Cart> removeItem(Mono<RemoveCartItemRequest> requestFlow) {
        return requestFlow
                .flatMap(request -> getPreAddItemRequestSupport(request)
                        .map(tuple -> {

                            final Cart cart = tuple.getT1();
                            final ProductStock product = tuple.getT2();

                            final CartItem item = tuple.getT3();
                            if (item.isEmpty()) {
                                throw new RuntimeException("Item was not present in the Cart");
                            }

                            final Integer quantity = Math.negateExact(request.getQuantity());
                            item.updateQuantity(quantity);

                            final Long additionalTotal = item.calculateSubTotal(quantity, product.getPrice());
                            cart.addToTotal(additionalTotal);

                            if (item.getQuantity() == 0) {
                                cartItemRepository.delete(item);
                            } else {
                                cartItemRepository.save(item);
                            }

                            return cartRepository.save(cart);
                        })
                );
    }

    private static void validateItemQuantity(ProductStock product, CartItem item) {
        final Integer currentQuantity = item.getQuantity();
        final Integer supportedQuantity = product.getQuantity();
        if (supportedQuantity < currentQuantity) {
            throw new RuntimeException("Quantity is unsupported.");
        }
    }

    @NonNull
    private Mono<Tuple3<Cart, ProductStock, CartItem>> getPreAddItemRequestSupport(CartItemRequest request) {
        return Mono.zip(
                retrieveAvailableCart(request),
                stockService.findOne(request),
                retrieveCartItem(request)
        );
    }

    @Override
    public Flux<ItemResource> getItems(Long id) {
        return Flux.fromIterable(cartItemRepository.findAllByCartId(id))
                   .map(cartItem -> {

                       final Long productReference = cartItem.getProductReference();
                       return stockService
                               .findOne(productReference)
                               .map(product -> {

                                   final Offer offer =
                                           Objects.requireNonNull(converter.convert(product, Offer.class));

                                   final OfferResource resource =
                                           Objects.requireNonNull(converter.convert(offer, OfferResource.class));

                                   final Integer quantity = cartItem.getQuantity();
                                   final Long subTotal = cartItem.getSubTotal(product.getPrice());

                                   return new ItemResource(resource, quantity, subTotal);
                               });
                   })
                   .flatMap(Mono::flux);
    }
}
