package com.leofuso.academy.distributed.computing.market.order.shopping.cart.service;

import javax.transaction.Transactional;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.order.product.stock.ProductStockService;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.AddCartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.Cart;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository.CartItemRepository;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository.CartRepository;

import reactor.core.publisher.Mono;

@Service
class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductStockService stockService;

    ShoppingCartServiceImpl(CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            ProductStockService stockService) {
        this.cartRepository = Objects.requireNonNull(cartRepository);
        this.cartItemRepository = Objects.requireNonNull(cartItemRepository);
        this.stockService = Objects.requireNonNull(stockService);
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
}
