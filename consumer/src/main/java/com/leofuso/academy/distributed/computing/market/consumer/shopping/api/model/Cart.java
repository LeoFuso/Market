package com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model;

import java.util.Objects;
import java.util.Set;

public class Cart {

    private final Long id;
    private final Long total;
    private Set<Item> items;

    public Cart(Long id, Long total, Set<Item> items) {
        this.id = Objects.requireNonNull(id);
        this.total = Objects.requireNonNull(total);
        this.items = Objects.requireNonNull(items);
    }

    public Long getId() {
        return id;
    }

    public Long getTotal() {
        return total;
    }

    public Set<Item> getItems() {
        return items;
    }

}
