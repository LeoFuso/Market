package com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class OfferResource implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;
    private final Integer quantity;

    public OfferResource(@NotNull final Long id,
                         @NotNull final String name,
                         @NotNull final String description,
                         @NotNull final Long price,
                         @NotNull Integer quantity) {
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
