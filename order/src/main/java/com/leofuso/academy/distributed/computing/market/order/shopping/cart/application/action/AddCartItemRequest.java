package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddCartItemRequest implements Serializable, CartItemRequest {

    @NotNull
    @Positive
    private final Long cartId;

    @NotNull
    @Positive
    private final Long offerId;

    @NotNull
    @Positive
    private final Integer quantity;

    @JsonCreator
    private static AddCartItemRequest valueOf(@JsonProperty("cart_id") @NotNull @Positive Long cartId,
                                              @JsonProperty("offer_id") @NotNull @Positive Long offerId,
                                              @NotNull @Positive Integer quantity) {
        return new AddCartItemRequest(cartId, offerId, quantity);
    }

    private AddCartItemRequest(Long cartId, Long offerId, Integer quantity) {
        this.cartId = Objects.requireNonNull(cartId);
        this.offerId = Objects.requireNonNull(offerId);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
