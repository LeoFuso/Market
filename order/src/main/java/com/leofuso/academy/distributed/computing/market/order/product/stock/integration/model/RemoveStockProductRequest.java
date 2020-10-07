package com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model;

import java.io.Serializable;
import java.util.List;

public class RemoveStockProductRequest implements Serializable {

    private final List<RemoveStockProductItemRequest> items;

    public RemoveStockProductRequest(List<RemoveStockProductItemRequest> items) {
        this.items = items;
    }

    public List<RemoveStockProductItemRequest> getItems() {
        return items;
    }
}
