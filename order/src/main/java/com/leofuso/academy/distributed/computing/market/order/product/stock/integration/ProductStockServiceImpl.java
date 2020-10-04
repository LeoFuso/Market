package com.leofuso.academy.distributed.computing.market.order.product.stock.integration;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.order.product.stock.ProductStockService;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;
import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockClient client;

    public ProductStockServiceImpl(ProductStockClient client) {
        this.client = Objects.requireNonNull(client);
    }

    @Override
    public Flux<ProductStock> list() {
        return client.list();
    }

    @Override
    public Mono<ProductStock> findOne(Long offerId) {
        return client.findOne(offerId);
    }

    @Override
    public Mono<ProductStock> findOne(CartItem cartItem) {
        final Long productReference = cartItem.getProductReference();
        return client.findOne(productReference);
    }
}
