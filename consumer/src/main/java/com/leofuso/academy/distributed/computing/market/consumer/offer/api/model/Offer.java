package com.leofuso.academy.distributed.computing.market.consumer.offer.api.model;

import java.util.Objects;

public class Offer {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;

    public Offer(Long id, String name, String description, Long price) {
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

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Offer))
            return false;

        Offer that = (Offer) o;
        return this.getId().equals(that.getId()) &&
                this.getName().equals(that.getName()) &&
                this.getDescription().equals(that.getDescription()) &&
                this.getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), this.getDescription(), this.getPrice());
    }
}
