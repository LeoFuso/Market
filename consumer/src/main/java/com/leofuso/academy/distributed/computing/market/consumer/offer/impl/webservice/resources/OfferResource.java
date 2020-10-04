package com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OfferResource implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;
    private final Integer quantity;

    @JsonCreator
    public OfferResource(@JsonProperty("id") final Long id,
                         @JsonProperty("name") final String name,
                         @JsonProperty("description") final String description,
                         @JsonProperty("price") final Long price,
                         @JsonProperty("quantity") Integer quantity) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
