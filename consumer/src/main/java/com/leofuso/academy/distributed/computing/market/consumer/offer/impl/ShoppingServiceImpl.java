package com.leofuso.academy.distributed.computing.market.consumer.offer.impl;

import java.util.Set;
import org.springframework.stereotype.Service;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.ShoppingService;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Offer;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Override
    public Set<Offer> listOffers() {
        return null;
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
