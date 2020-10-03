package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.shopping.resource.CartResource;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.service.ShoppingCartService;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService service;

    public ShoppingCartController(final ShoppingCartService service) {
        this.service = Objects.requireNonNull(service);
    }

    public ResponseEntity<CartResource> create() {
        return null;
    }

}
