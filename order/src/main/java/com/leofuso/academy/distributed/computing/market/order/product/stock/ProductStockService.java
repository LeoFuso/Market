package com.leofuso.academy.distributed.computing.market.order.product.stock;

import org.springframework.web.reactive.function.client.ClientResponse;

import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.RemoveStockProductRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.CartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductStockService {

    Flux<ProductStock> list();

    Mono<ProductStock> findOne(Long offerId);

    Mono<ProductStock> findOne(CartItem cartItem);

    Mono<ProductStock> findOne(CartItemRequest request);

    Mono<ClientResponse> remove(RemoveStockProductRequest request);
}
