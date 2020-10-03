package com.leofuso.academy.distributed.computing.market.product.stock.application.resource;

import javax.validation.constraints.NotNull;

import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResource extends RepresentationModel<ProductResource> {

    @NotNull
    private final Long id;

    @NotNull
    private final String name;

    @NotNull
    private final String description;

    @NotNull
    private final Long price;

    @NotNull
    private final Integer quantity;

    @NotNull
    private final Instant lastModifiedDate;

    public ProductResource(@NotNull final Long id,
                           @NotNull final String name,
                           @NotNull final String description,
                           @NotNull final Long price,
                           @NotNull final Integer quantity,
                           @NotNull final Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.lastModifiedDate = lastModifiedDate;
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

    @JsonProperty("last_modified_date")
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

}
