package com.leofuso.academy.distributed.computing.market.order.shopping.cart.service;

import javax.transaction.Transactional;

import java.util.Objects;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;
import com.leofuso.academy.distributed.computing.market.order.offer.model.Offer;
import com.leofuso.academy.distributed.computing.market.order.product.stock.ProductStockService;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.AddCartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource.ItemResource;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.Cart;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository.CartItemRepository;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository.CartRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    @Transactional
    public Mono<Cart> addItem(Mono<AddCartItemRequest> requestFlow) {
        return requestFlow
                .map(request -> {

                    final Long cartId = request.getCartId();
                    final Long offerId = request.getOfferId();
                    final Integer quantity = request.getQuantity();

                    final Cart cartReference = cartRepository.findById(cartId)
                                                             .orElseThrow();

                    final CartItem item = new CartItem();
                    item.setCart(cartReference);
                    item.setProductReference(offerId);
                    item.setQuantity(quantity);

                    return cartItemRepository.save(item);
                })
                .flatMap(cartItem -> stockService.findOne(cartItem)
                                                 .map(ProductStock::getPrice)
                                                 .map(cartItem::calculatePrice)
                                                 .map(cartItem::addToTotal))
                .map(cartRepository::save);
    }

    @Override
    public Flux<ItemResource> getItems(Long id) {
        return Flux.fromIterable(cartItemRepository.findAllByCartId(id))
                   .map(cartItem -> {

                       final Long productReference = cartItem.getProductReference();
                       return stockService
                               .findOne(productReference)
                               .map(product -> {

                                   final Offer offer = converter.convert(product, Offer.class);
                                   Objects.requireNonNull(offer);

                                   return offer;
                               })
                               .map(offer -> {

                                   final OfferResource resource = converter.convert(offer, OfferResource.class);
                                   Objects.requireNonNull(resource);
                                   return resource;

                               })
                               .map(resource -> new ItemResource(resource, cartItem.getQuantity()));

                   })
                   .flatMap(Mono::flux);
    }
}
