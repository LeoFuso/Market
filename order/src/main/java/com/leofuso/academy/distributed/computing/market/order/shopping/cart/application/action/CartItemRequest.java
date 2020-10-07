package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action;

public interface CartItemRequest {
    Long getCartId();

    Long getOfferId();

    Integer getQuantity();
}
