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

    public static Cart empty() {

        final Cart cart = new Cart();
        cart.state = State.OPEN;

        return cart;
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
}
