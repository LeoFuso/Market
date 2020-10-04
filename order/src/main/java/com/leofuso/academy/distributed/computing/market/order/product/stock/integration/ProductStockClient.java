package com.leofuso.academy.distributed.computing.market.order.product.stock.integration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductStockClient {

    private final WebClient client;

    public ProductStockClient(final WebClient.Builder builder) {
        this.client = builder
                .baseUrl("http://localhost:25565")
                .build();
    }

    public Flux<ProductStock> list() {
        return client.get()
                     .uri("/products")
                     .retrieve()
                     .bodyToFlux(ProductStock.class)
                     .onErrorResume(Mono::error);
    }

    public Mono<ProductStock> findOne(Long productId) {
        return client.get()
                     .uri("/products/" + productId)
                     .retrieve()
                     .bodyToMono(ProductStock.class)
                     .onErrorResume(Mono::error);
    }
}
