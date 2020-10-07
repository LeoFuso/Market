package com.leofuso.academy.distributed.computing.market.order.shopping.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.leofuso.academy.distributed.computing.market.order.common.model.AbstractMutableEntity;

@Entity
public class Cart extends AbstractMutableEntity {

    public enum State {
        OPEN,
        CLOSED
    }


    @Column(nullable = false)
    private Cart.State state;

    @Column(nullable = false)
    private Long total;

    public static Cart empty() {

        final Cart cart = new Cart();
        cart.state = State.OPEN;
        cart.total = 0L;

        return cart;
    }

    public Boolean isEmpty() {
        return total == 0L;
    }

    public void close() {

        final Boolean empty = isEmpty();
        if (empty) {
            throw new RuntimeException("Cart is empty or unavailable");
        }

        final Boolean available = isAvailable();
        if (!available) {
            throw new RuntimeException("Cart is unavailable");
        }

        state = State.CLOSED;
    }

    public void addToTotal(Long additionalTotal) {
        if (null == total) {
            total = 0L;
        }

        if (total + additionalTotal <= 0) {
            throw new RuntimeException("Illegal price value");
        }

        total += additionalTotal;
    }

    public Boolean isAvailable() {
        return state == State.OPEN;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
