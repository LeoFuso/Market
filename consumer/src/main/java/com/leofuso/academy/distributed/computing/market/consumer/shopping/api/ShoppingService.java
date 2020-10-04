package com.leofuso.academy.distributed.computing.market.consumer.shopping.api;

import java.util.List;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Offer;

public interface ShoppingService {

    List<Offer> listOffers();

    Cart createCart();

    Cart retrieveCart (final Long cartId);

    Cart addItem (final Long cartId, final Long offerId, final Integer quantityToAdd);

    Cart removeItem (final Long cartId, final Long offerId, final Integer quantityToRemove);

    Cart finishOrder (final Long cartId);

}
