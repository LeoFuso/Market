package com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductStock implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final Long price;
    private final Integer quantity;

    public ProductStock(final Long id,
                        final String name,
                        final String description,
                        final Long price,
                        final Integer quantity) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.description = Objects.requireNonNull(description);
        this.price = Objects.requireNonNull(price);
        this.quantity = Objects.requireNonNull(quantity);
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

    public Integer getQuantity() {
        return quantity;
    }

    public static boolean isAvailable(final ProductStock product) {
        return product.getQuantity() >= 1;
    }

}
