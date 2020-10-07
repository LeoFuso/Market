package com.leofuso.academy.distributed.computing.market.product.stock.application.action;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveStockProductItemRequest implements Serializable {

    private final Long productId;
    private final Integer quantity;

    @JsonCreator
    public RemoveStockProductItemRequest(@JsonProperty("product_id") Long productId, Integer quantity) {
        this.productId = Objects.requireNonNull(productId);
        this.quantity = Objects.requireNonNull(quantity);
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
