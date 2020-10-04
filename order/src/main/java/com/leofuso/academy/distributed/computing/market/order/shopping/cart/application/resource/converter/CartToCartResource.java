package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.leofuso.academy.distributed.computing.market.order.common.annotation.ConverterComponent;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource.CartResource;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.Cart;

@ConverterComponent
public class CartToCartResource implements Converter<Cart, CartResource> {

    @Override
    public CartResource convert(@NonNull Cart source) {
        return new CartResource(source.getId(), source.getState());
    }

}
