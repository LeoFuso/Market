package com.leofuso.academy.distributed.computing.market.order.shopping.cart.application;

import javax.validation.Valid;

import java.util.Objects;

import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.action.AddCartItemRequest;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource.CartResource;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.application.resource.ItemResource;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.service.ShoppingCartService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService service;
    private final ConversionService converter;

    public ShoppingCartController(final ShoppingCartService service, final ConversionService conversionService) {
        this.service = Objects.requireNonNull(service);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @PostMapping(produces = "application/json")
    public Mono<CartResource> create() {
        return service.create()
                      .map(cart -> {
                          final CartResource resource = converter.convert(cart, CartResource.class);
                          Objects.requireNonNull(resource);
                          return resource;
                      });
    }

    @PostMapping(
            value = "/{id}/cart-items",
            consumes = "application/json",
            produces = "application/json"
    )
    public Mono<CartResource> addItem(@PathVariable Long id,
                                      @Valid @RequestBody final Mono<AddCartItemRequest> request) {
        return service.addItem(request)
                      .map(cart -> {
                          final CartResource resource = converter.convert(cart, CartResource.class);
                          Objects.requireNonNull(resource);
                          return resource;
                      });
    }

    @GetMapping(value = "/{id}/cart-items")
    public Flux<ItemResource> getItems(@PathVariable Long id) {
        return service.getItems(id);
    }

}
