package com.leofuso.academy.distributed.computing.market.order.offer.service;

import com.leofuso.academy.distributed.computing.market.order.offer.model.Offer;

import reactor.core.publisher.Flux;

public interface OfferService {

    Flux<Offer> list();

}
