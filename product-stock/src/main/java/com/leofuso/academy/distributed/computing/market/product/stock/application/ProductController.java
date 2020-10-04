package com.leofuso.academy.distributed.computing.market.product.stock.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leofuso.academy.distributed.computing.market.product.stock.application.resource.ProductResource;
import com.leofuso.academy.distributed.computing.market.product.stock.domain.Product;
import com.leofuso.academy.distributed.computing.market.product.stock.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService service;
    private final ConversionService converter;

    public ProductController(final ProductService service, final ConversionService conversionService) {
        this.service = Objects.requireNonNull(service);
        converter = Objects.requireNonNull(conversionService);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getProducts() {

        final List<Product> products = service.list();
        final List<ProductResource> resources = products.stream()
                                                        .map(product -> converter.convert(
                                                                product,
                                                                ProductResource.class))
                                                        .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getOneProduct(@PathVariable Long id) {
        final Optional<ProductResource> resource =
                service.findOne(id)
                       .map(product -> converter.convert(product, ProductResource.class));

        return resource.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound()
                                                      .build());
    }

}
