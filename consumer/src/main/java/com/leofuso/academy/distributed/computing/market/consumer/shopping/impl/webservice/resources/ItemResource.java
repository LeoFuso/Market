package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemResource implements Serializable {

    private final OfferResource offer;
    private final Integer quantity;

    @JsonCreator
    public ItemResource(@JsonProperty("offer") final OfferResource offer,
                        @JsonProperty("quantity") final Integer quantity) {
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
