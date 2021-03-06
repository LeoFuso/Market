package com.leofuso.academy.distributed.computing.market.order.offer.application.resource;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;


public class OfferResource implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;

    public OfferResource(@NotNull final Long id,
                         @NotNull final String name,
                         @NotNull final String description,
                         @NotNull final Long price) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
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

}
