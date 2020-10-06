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
        return this.offer;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Long getSubTotal() {
        return this.subTotal;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        final Item that = (Item) o;
        return this.getOffer().equals(that.getOffer()) &&
                this.getQuantity().equals(that.getQuantity()) &&
                this.getSubTotal().equals(that.getSubTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getOffer(), this.getQuantity(), this.getSubTotal());
    }
}
