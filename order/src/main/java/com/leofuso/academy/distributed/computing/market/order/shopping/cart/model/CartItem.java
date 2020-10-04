package com.leofuso.academy.distributed.computing.market.order.shopping.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.leofuso.academy.distributed.computing.market.order.common.model.AbstractMutableEntity;

@Entity
public class CartItem extends AbstractMutableEntity {

    @JoinColumn
    @ManyToOne(optional = false)
    private Cart cart;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Long productReference;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantity;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getProductReference() {
        return productReference;
    }

    public void setProductReference(Long productReference) {
        this.productReference = productReference;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void updateQuantity(Integer quantity) {
        this.quantity+=quantity;
    }
}
