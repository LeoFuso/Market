package com.leofuso.academy.distributed.computing.market.order.offer.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;

import reactor.core.publisher.Flux;

@Service
class OfferServiceImpl implements OfferService {

    private final OfferWebService webService;

    OfferServiceImpl(OfferWebService webService) {
        this.webService = Objects.requireNonNull(webService);
    }

    @Override
    public Flux<OfferResource> list() {
        return webService.list();
    }
}
