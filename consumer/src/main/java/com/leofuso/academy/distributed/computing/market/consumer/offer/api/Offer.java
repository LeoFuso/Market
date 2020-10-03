package com.leofuso.academy.distributed.computing.market.consumer.offer.api;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Offer {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;

    public Offer(@NotNull Long id, @NotNull String name, @NotNull String description, @NotNull Long price) {
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
