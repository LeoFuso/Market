package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;

public class ItemResource implements Serializable {

    private final OfferResource offer;
    private final Integer quantity;
    private final Long subTotal;

    public ItemResource(@NonNull final OfferResource offer, @NonNull Integer quantity, @NonNull final Long subTotal) {
        this.offer = Objects.requireNonNull(offer);
        this.quantity = Objects.requireNonNull(quantity);
        this.subTotal = subTotal;
    }

    public OfferResource getOffer() {
        return offer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @JsonProperty("sub_total")
    public Long getSubTotal() {
        return subTotal;
    }
}
