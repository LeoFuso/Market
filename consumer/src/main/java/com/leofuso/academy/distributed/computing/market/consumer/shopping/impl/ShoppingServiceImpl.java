package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl;

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

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private final OrderWebService orderWebService;
    private final ConversionService converter;

    public ShoppingServiceImpl(final OrderWebService orderWebService,
                               final ConversionService webFluxConversionService) {
        this.orderWebService = Objects.requireNonNull(orderWebService);
        this.converter = Objects.requireNonNull(webFluxConversionService);
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
                .map(resource -> converter.convert(resource, Cart.class))
                .block();
    }

    @Override
    public Cart retrieveCart(final Long cartId) {

        return orderWebService
                .retrieveCart(cartId)
                .map(resource -> converter.convert(resource, Cart.class))
                .block();
    }

    @Override
    public Cart addItem(final Long cartId, final Long offerId, final Integer quantity) {

        return orderWebService
                .addItem(cartId, offerId, quantity)
                .map(resource -> converter.convert(resource, Cart.class))
                .block();
    }

    @Override
    public Cart removeItem(final Long cartId, final Long offerId, final Integer quantity) {

        return orderWebService
                .removeItems(cartId, offerId, quantity)
                .map(resource -> converter.convert(resource, Cart.class))
                .block();
    }

    @Override
    public Cart finishOrder(final Long cartId) {

        return orderWebService
                .finishOrder(cartId)
                .map(resource -> converter.convert(resource, Cart.class))
                .block();
    }
}
