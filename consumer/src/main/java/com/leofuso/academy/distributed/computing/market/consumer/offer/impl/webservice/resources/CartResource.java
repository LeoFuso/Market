package com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartResource implements Serializable {

    private final Set<ItemResource> items;
    private final Long price;

    @JsonCreator
    public CartResource(@JsonProperty("items") final Set<ItemResource> items,
                        @JsonProperty("price") final Long price) {
        this.items = Objects.requireNonNull(items);
        this.price = Objects.requireNonNull(price);
    }

    public Set<ItemResource> getItens() {
        return items;
    }

    public Long getPrice() {
        return price;
    }
}
