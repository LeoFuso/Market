package com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model;

import java.util.Objects;

public class Item {

    private final Offer offer;
    private final Integer quantity;

    public Item(Offer offer, Integer quantity) {
        this.offer = Objects.requireNonNull(offer);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public Offer getOffer() {
        return offer;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
