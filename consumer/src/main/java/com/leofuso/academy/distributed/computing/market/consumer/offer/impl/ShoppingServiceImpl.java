package com.leofuso.academy.distributed.computing.market.consumer.offer.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.ShoppingService;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Offer;
import com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice.OrderWebService;

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
        return null;
    }

    @Override
    public Cart addItem(Long cartId, Long offerId, Integer quantityToAdd) {
        return null;
    }

    @Override
    public Cart removeItem(Long cartId, Long offerId, Integer quantityToRemove) {
        return null;
    }

    @Override
    public Cart retrieveCart(Long cartId) {
        return null;
    }

    @Override
    public Cart finishOrder(Long cartId) {
        return null;
    }
}
