package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.shopping.resource;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class CartResource implements Serializable {

    private final Set<ItemResource> itens;
    private final Long price;

    public CartResource(final Set<ItemResource> itens, final Long price) {
        this.itens = Objects.requireNonNull(itens);
        this.price = Objects.requireNonNull(price);
    }

    public Set<ItemResource> getItens() {
        return itens;
    }

    public Long getPrice() {
        return price;
    }
}
