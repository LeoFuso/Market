package com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice.resources.OfferResource;

import reactor.core.publisher.Flux;

@Component
public class OrderWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWebService.class);
    private final WebClient webClient;

    public OrderWebService(@Qualifier("orderWebClient") WebClient webClient) {
        this.webClient = Objects.requireNonNull(webClient);
    }

    public Flux<OfferResource> retrieveOffers () {

        return webClient
                .get()
                .uri("/offers")
                .retrieve()
                .bodyToFlux(OfferResource.class)
                .doOnError(throwable -> LOGGER.error("Error while requesting for offers: ", throwable));
    }

}
