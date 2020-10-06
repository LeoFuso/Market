package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.ShoppingService;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Offer;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.OrderWebService;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.CartResource;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.ItemResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final OrderWebService orderWebService;
    private final ConversionService converter;

    public ShoppingServiceImpl(final OrderWebService orderWebService,
                               final ConversionService conversionService) {
        this.orderWebService = Objects.requireNonNull(orderWebService);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @Override
    public List<Offer> listOffers() {

        return orderWebService
                .retrieveOffers()
                .map(resource -> converter.convert(resource, Offer.class))
                .toStream()
                .sorted(Comparator.comparingLong(Offer::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Cart createCart() {

        return orderWebService
                .createCart()
                .map(resource -> {
                    final Tuple2<CartResource, List<ItemResource>> pair = Tuples.of(resource, Collections.emptyList());
                    return Objects.requireNonNull(converter.convert(pair, Cart.class));
                })
                .block();
    }

    @Override
    public Cart retrieveCart(final Long cartId) {
        final Mono<CartResource> retrievingCartProducer = orderWebService.retrieveCart(cartId);
        return fullCartUsing(retrievingCartProducer, cartId);
    }

    @Override
    public Cart addItem(final Long cartId, final Long offerId, final Integer quantity) {
        final Mono<CartResource> addingCartProducer = orderWebService.addItem(cartId, offerId, quantity);
        return fullCartUsing(addingCartProducer, cartId);
    }

    @Override
    public Cart removeItem(final Long cartId, final Long offerId, final Integer quantity) {
        final Mono<CartResource> removingCartProducer = orderWebService.removeItems(cartId, offerId, quantity);
        return fullCartUsing(removingCartProducer, cartId);
    }

    @Override
    public Cart finishOrder(final Long cartId) {
        final Mono<CartResource> finishingOrderCartProducer = orderWebService.finishOrder(cartId);
        return fullCartUsing(finishingOrderCartProducer, cartId);
    }

    private Cart fullCartUsing(final Mono<CartResource> cartResourceProducer, final Long cartId) {

        final Flux<ItemResource> retrievingItemsProducer = orderWebService.itemsFromCart(cartId);
        return Mono.zip(cartResourceProducer, retrievingItemsProducer.collectList())
                .map(pair -> Objects.requireNonNull(converter.convert(pair, Cart.class)))
                .block();
    }
}
