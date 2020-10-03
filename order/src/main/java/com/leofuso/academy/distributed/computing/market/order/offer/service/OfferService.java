package com.leofuso.academy.distributed.computing.market.order.offer.service;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;

import reactor.core.publisher.Flux;

public interface OfferService {

    Flux<OfferResource> list();

}
