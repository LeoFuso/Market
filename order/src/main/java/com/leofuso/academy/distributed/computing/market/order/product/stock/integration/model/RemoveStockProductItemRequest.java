package com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveStockProductItemRequest implements Serializable {

    private final Long productId;
    private final Integer quantity;

    public RemoveStockProductItemRequest(Long productId, Integer quantity) {
        this.productId = Objects.requireNonNull(productId);
        this.quantity = Objects.requireNonNull(quantity);
    }

    @JsonProperty("product_id")
    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
