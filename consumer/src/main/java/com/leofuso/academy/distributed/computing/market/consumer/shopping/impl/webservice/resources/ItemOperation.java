package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources;

import java.util.Objects;

public class ItemOperation {

    private final Long offerId;
    private final Integer quantity;

    public ItemOperation(final Long offerId, final Integer quantity) {
        this.offerId = Objects.requireNonNull(offerId);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public Long getOfferId() {
        return offerId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
