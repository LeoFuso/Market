package com.leofuso.academy.distributed.computing.market.product.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import java.util.Objects;

import com.leofuso.academy.distributed.computing.market.product.stock.common.model.AbstractMutableEntity;

@Entity
public class Product extends AbstractMutableEntity {

    @NotNull
    @Column(nullable = false,
            unique = true)
    private String name;

    @NotNull
    @Column(nullable = false,
            unique = true)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Long price;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(final Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final Product product = (Product) o;
        return getName().equals(product.getName()) &&
                getDescription().equals(product.getDescription()) &&
                getPrice().equals(product.getPrice()) &&
                getQuantity().equals(product.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getDescription(), getPrice(), getQuantity());
    }
}
