package com.leofuso.academy.distributed.computing.market.order.shopping.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    public static CartItem ofEmpty() {
        return new CartItem();
    }

    public Boolean isEmpty() {
        return cart == null || productReference == null || quantity == null;
    }

    public Long getSubTotal(Long unityPrice) {
        return calculateSubTotal(quantity, unityPrice);
    }

    public Long calculateSubTotal(Integer quantity, Long unityPrice) {
        return quantity * unityPrice;
    }

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
        if (this.quantity == null) {
            this.quantity = 0;
        }

        if(this.quantity + quantity < 0) {
            throw new RuntimeException("Illegal quantity.");
        }

        this.quantity += quantity;
    }
}
