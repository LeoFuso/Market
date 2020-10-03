package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.shopping.resource;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.lang.NonNull;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;

public class ItemResource implements Serializable {

    private final OfferResource offer;
    private final Integer quantity;

    public ItemResource(@NonNull final OfferResource offer,@NonNull final Integer quantity) {
        this.offer = Objects.requireNonNull(offer);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public OfferResource getOffer() {
        return offer;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
