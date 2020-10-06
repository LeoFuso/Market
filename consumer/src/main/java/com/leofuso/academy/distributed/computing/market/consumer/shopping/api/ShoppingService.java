package com.leofuso.academy.distributed.computing.market.consumer.shopping.api;

import java.util.List;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Offer;

public interface ShoppingService {

    List<Offer> listOffers();

    Cart createCart();

    Cart retrieveCart (Long cartId);

    Cart addItem (Long cartId, Long offerId, Integer quantityToAdd);

    Cart removeItem (Long cartId, Long offerId, Integer quantityToRemove);

    Cart finishOrder (Long cartId);

}
