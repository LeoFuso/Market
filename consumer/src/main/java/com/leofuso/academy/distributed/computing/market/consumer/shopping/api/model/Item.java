package com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model;

import java.util.Objects;

public class Item {

    private final Offer offer;
    private final Integer quantity;
    private final Long subTotal;

    public Item(final Offer offer, final Integer quantity, final Long subTotal) {
        this.offer = Objects.requireNonNull(offer);
        this.quantity = Objects.requireNonNull(quantity);
        this.subTotal = Objects.requireNonNull(subTotal);
    }

    public Offer getOffer() {
        return offer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getSubTotal() {
        return subTotal;
    }
}
