package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemResource implements Serializable {

    private final OfferResource offer;
    private final Integer quantity;
    private final Long subTotal;

    @JsonCreator
    public ItemResource(@JsonProperty("offer") final OfferResource offer,
                        @JsonProperty("quantity") final Integer quantity,
                        @JsonProperty("sub_total") final Long subTotal) {
        this.offer = Objects.requireNonNull(offer);
        this.quantity = Objects.requireNonNull(quantity);
        this.subTotal = Objects.requireNonNull(subTotal);
    }

    public OfferResource getOffer() {
        return this.offer;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Long getSubTotal() {
        return this.subTotal;
    }
}
