package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartResource implements Serializable {

    private final Long id;
    private final Long price;
    private final Set<ItemResource> items;

    @JsonCreator
    public CartResource(@JsonProperty("id") final Long id,
                        @JsonProperty("items") final Set<ItemResource> items,
                        @JsonProperty("price") final Long price) {
        this.id = Objects.requireNonNull(id);
        this.items = Objects.requireNonNull(items);
        this.price = Objects.requireNonNull(price);
    }

    public Long getId() {
        return id;
    }

    public Set<ItemResource> getItems() {
        return items;
    }

    public Long getPrice() {
        return price;
    }
}
