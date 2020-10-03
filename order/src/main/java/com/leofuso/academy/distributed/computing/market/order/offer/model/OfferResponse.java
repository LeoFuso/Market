package com.leofuso.academy.distributed.computing.market.order.offer.model;

import javax.validation.constraints.NotNull;

import java.util.List;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;

public class OfferResponse {

    private final List<OfferResource> offers;

    public OfferResponse(@NotNull List<OfferResource> offers) {
        this.offers = offers;
    }

    public List<OfferResource> getOffers() {
        return offers;
    }

}
