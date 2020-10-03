package com.leofuso.academy.distributed.computing.market.order.offer.application;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;
import com.leofuso.academy.distributed.computing.market.order.offer.service.OfferService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService service;

    public OfferController(OfferService service) {
        this.service = Objects.requireNonNull(service);
    }

    @GetMapping
    public Flux<OfferResource> getOffers() {
        return service.list();
    }

}
