package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource;

import java.io.Serializable;
import java.util.Objects;

import static com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.Cart.State;

public class CartResource implements Serializable {

    private final Long id;
    private final State state;
    private final Long total;

    public CartResource(final Long id, final State state, final Long total) {
        this.id = Objects.requireNonNull(id);
        this.state = Objects.requireNonNull(state);
        this.total = Objects.requireNonNull(total);
    }

    public Long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public Long getTotal() {
        return total;
    }
}
