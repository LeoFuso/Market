package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OfferResource implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;

    @JsonCreator
    public OfferResource(@JsonProperty("id") final Long id,
                         @JsonProperty("name") final String name,
                         @JsonProperty("description") final String description,
                         @JsonProperty("price") final Long price) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getPrice() {
        return this.price;
    }
}
