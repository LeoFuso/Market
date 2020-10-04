package com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class CartResource implements Serializable {

    private final Set<ItemResource> itens;
    private final Long price;

    public CartResource(@NotNull final Set<ItemResource> itens,
                        @NotNull final Long price) {
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
