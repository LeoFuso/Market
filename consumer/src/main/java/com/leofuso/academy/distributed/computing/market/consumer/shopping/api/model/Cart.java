package com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model;

import java.util.Objects;
import java.util.Set;

public class Cart {

    private final Long id;
    private final Set<Item> items;
    private final State state;

    public Cart(final Long id, final Set<Item> items, final State state) {
        this.id = Objects.requireNonNull(id);
        this.items = Objects.requireNonNull(items);
        this.state = Objects.requireNonNull(state);
    }

    public Long getId() {
        return id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public State getState() {
        return state;
    }

    public Long total() {
        return items
                .stream()
                .map(Item::getSubTotal)
                .reduce(Long::sum)
                .orElse(0L);
    }


}
