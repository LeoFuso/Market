package com.leofuso.academy.distributed.computing.market.order.offer.model;

import java.util.Objects;

public class SimpleOffer implements Offer {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;

    public SimpleOffer(Long id, String name, String description, Long price) {
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
