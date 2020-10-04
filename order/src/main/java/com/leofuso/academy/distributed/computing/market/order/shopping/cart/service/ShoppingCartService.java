package com.leofuso.academy.distributed.computing.market.order.shopping.cart.service;

import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.AddCartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource.ItemResource;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.Cart;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ShoppingCartService {

    Mono<Cart> create();

    Mono<Cart> addItem(Mono<AddCartItemRequest> request);

    Flux<ItemResource> getItems(Long id);
}
