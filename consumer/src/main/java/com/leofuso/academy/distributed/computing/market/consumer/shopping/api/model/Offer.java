package com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model;

import java.util.Objects;

public class Offer {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;

    public Offer(final Long id, final String name, final String description, final Long price) {
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

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }

        final Offer that = (Offer) o;
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
