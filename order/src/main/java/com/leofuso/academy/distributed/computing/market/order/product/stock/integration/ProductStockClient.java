package com.leofuso.academy.distributed.computing.market.order.product.stock.integration;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.RemoveStockProductRequest;

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

    public Mono<ClientResponse> remove(RemoveStockProductRequest request) {
        return client.method(HttpMethod.DELETE)
              .uri("/products/batch")
              .body(BodyInserters.fromValue(request))
              .exchange()
              .map(clientResponse -> {

                  System.out.println(clientResponse.statusCode());
                  return clientResponse;

              })
              .onErrorResume(Mono::error);
    }
}
