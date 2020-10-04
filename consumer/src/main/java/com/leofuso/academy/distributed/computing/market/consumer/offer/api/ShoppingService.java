package com.leofuso.academy.distributed.computing.market.consumer.offer.api;

import java.util.Set;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Offer;

public interface ShoppingService {

    Set<Offer> listOffers();

    Cart createCart();

    Cart addItem (Long cartId, Long offerId, Integer quantityToAdd);

    Cart removeItem (Long cartId, Long offerId, Integer quantityToRemove);

    Cart retrieveCart (Long cartId);

    Cart finishOrder (Long cartId);

}
