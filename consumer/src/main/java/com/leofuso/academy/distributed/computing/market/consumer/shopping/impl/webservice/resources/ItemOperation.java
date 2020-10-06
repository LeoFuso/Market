package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ItemOperation implements Serializable {

    private final Long cartId;
    private final Long offerId;
    private final Integer quantity;

    public ItemOperation(final Long cartId, final Long offerId, final Integer quantity) {
        this.cartId = Objects.requireNonNull(cartId);
        this.offerId = Objects.requireNonNull(offerId);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public Long getCartId() {
        return this.cartId;
    }

    public Long getOfferId() {
        return this.offerId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }
}
