package com.leofuso.academy.distributed.computing.market.product.stock.application.action;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RemoveStockProductRequest implements Serializable {

    private final List<RemoveStockProductItemRequest> items;

    @JsonCreator
    public RemoveStockProductRequest(List<RemoveStockProductItemRequest> items) {
        this.items = items;
    }

    public List<RemoveStockProductItemRequest> getItems() {
        return items;
    }
}
