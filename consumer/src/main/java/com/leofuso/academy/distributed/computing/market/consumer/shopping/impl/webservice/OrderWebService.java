package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.CartResource;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.ItemOperation;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.ItemResource;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.OfferResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OrderWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWebService.class);

    private final WebClient webClient;

    public OrderWebService(@Qualifier("orderWebClient") final WebClient webClient) {
        this.webClient = Objects.requireNonNull(webClient);
    }

    public Flux<OfferResource> retrieveOffers () {

        return this.webClient
                .get()
                .uri("/offers")
                .retrieve()
                .bodyToFlux(OfferResource.class)
                .doOnError(throwable -> LOGGER.error("Error while requesting for offers: ", throwable));
    }

    public Mono<CartResource> createCart () {

        return this.webClient
                .post()
                .uri("/shopping-cart")
                .retrieve()
                .bodyToMono(CartResource.class)
                .doOnError(throwable -> LOGGER.error("Error while requesting the creation of a new cart: ", throwable));
    }

    public Flux<ItemResource> itemsFromCart(final Long cartId) {

        return this.webClient
                .get()
                .uri(builder -> builder
                        .path("/shopping-cart/{id}/cart-items")
                        .build(cartId))
                .retrieve()
                .bodyToFlux(ItemResource.class)
                .doOnError(throwable -> LOGGER.error("Error while retrieving all items from cart: ", throwable));
    }

    public Mono<CartResource> retrieveCart (final Long cartId) {

        return this.webClient
                .get()
                .uri(builder -> builder
                        .path("/shopping-cart/{id}")
                        .build(cartId))
                .retrieve()
                .bodyToMono(CartResource.class)
                .doOnError(throwable -> LOGGER.error("Error while requesting cart: ", throwable));
    }

    public Mono<CartResource> addItem (final Long cartId, final Long offerId, final Integer quantity) {

        final Mono<ItemOperation> operation = Mono.just(new ItemOperation(cartId, offerId, quantity));

        return this.webClient
                .post()
                .uri(builder -> builder
                        .path("/shopping-cart/{id}/cart-items")
                        .build(cartId))
                .body(operation, ItemOperation.class)
                .retrieve()
                .bodyToMono(CartResource.class)
                .doOnError(throwable -> LOGGER.error("Error while requesting the adding of items to cart: ", throwable));
    }

    public Mono<CartResource> removeItems (final Long cartId, final Long offerId, final Integer quantity) {

        final Mono<ItemOperation> operation = Mono.just(new ItemOperation(cartId, offerId, quantity));

        return this.webClient
                .method(HttpMethod.DELETE)
                .uri(builder -> builder
                        .path("/shopping-cart/{id}/items")
                        .build(cartId))
                .body(operation, ItemOperation.class)
                .retrieve()
                .bodyToMono(CartResource.class)
                .doOnError(throwable -> LOGGER.error("Error while requesting the removal of items from cart: ", throwable));
    }

    public Mono<CartResource> finishOrder (final Long cartId) {

        return this.webClient
                .post()
                .uri(builder -> builder
                        .path("/shopping-cart/order/{id}")
                        .build(cartId))
                .retrieve()
                .bodyToMono(CartResource.class)
                .doOnError(throwable -> LOGGER.error("Error while requesting creation of order from cart: ", throwable));
    }


}
