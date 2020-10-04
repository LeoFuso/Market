package com.leofuso.academy.distributed.computing.market.order.product.stock;

import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductStockService {

    Flux<ProductStock> list();

    Mono<ProductStock> findOne(Long offerId);

    Mono<ProductStock> findOne(CartItem cartItem);
}
