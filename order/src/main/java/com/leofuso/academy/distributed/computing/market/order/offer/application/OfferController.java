package com.leofuso.academy.distributed.computing.market.order.offer.application;

import java.util.Objects;

import org.springframework.core.convert.ConversionService;
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
    private final ConversionService converter;

    public OfferController(OfferService service, ConversionService conversionService) {
        this.service = Objects.requireNonNull(service);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @GetMapping
    public Flux<OfferResource> getOffers() {
        return service.list()
                .map(offer -> {

                    final OfferResource resource = converter.convert(offer, OfferResource.class);
                    Objects.requireNonNull(resource);

                    return resource;
                });
    }

}
