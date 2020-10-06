package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.State;

public class CartResource implements Serializable {

    private final Long id;
    private final State state;

    @JsonCreator
    public CartResource(@JsonProperty("id") final Long id,
                        @JsonProperty("state") final State state) {
        this.id = Objects.requireNonNull(id);
        this.state = Objects.requireNonNull(state);
    }

    public Long getId() {
        return id;
    }

    public State getState() {
        return state;
    }
}
