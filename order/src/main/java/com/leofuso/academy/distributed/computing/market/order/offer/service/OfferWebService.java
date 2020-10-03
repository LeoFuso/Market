package com.leofuso.academy.distributed.computing.market.order.offer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;

import reactor.core.publisher.Flux;

@Component
public class OfferWebService {

    private final WebClient client;

    public OfferWebService(final WebClient.Builder builder) {
        this.client = builder
                .baseUrl("http://localhost:25565")
                .build();
    }

    public Flux<OfferResource> list() {
        return client.get()
                     .uri("/products")
                     .retrieve()
                     .bodyToFlux(OfferResource.class)
                     .filter(offerResource -> offerResource.getQuantity() >= 1)
                     .doOnError(Throwable::printStackTrace);
    }
}
