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
        cart.total = 0L;
        cart.state = State.OPEN;

        return cart;
    }

    public void addToTotal(Long newEntry) {
        total += newEntry;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
