package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveCartItemRequest implements Serializable, CartItemRequest {

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
    private static RemoveCartItemRequest valueOf(@JsonProperty("cart_id") @NotNull @Positive Long cartId,
                                                 @JsonProperty("offer_id") @NotNull @Positive Long offerId,
                                                 @NotNull @Positive Integer quantity) {
        return new RemoveCartItemRequest(cartId, offerId, quantity);
    }

    private RemoveCartItemRequest(Long cartId, Long offerId, Integer quantity) {
        this.cartId = Objects.requireNonNull(cartId);
        this.offerId = Objects.requireNonNull(offerId);
        this.quantity = Objects.requireNonNull(quantity);
    }

    @Override
    public Long getCartId() {
        return cartId;
    }

    @Override
    public Long getOfferId() {
        return offerId;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

}
