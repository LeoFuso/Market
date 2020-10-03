package com.leofuso.academy.distributed.computing.market.product.stock.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.product.stock.domain.Product;
import com.leofuso.academy.distributed.computing.market.product.stock.repository.ProductRepository;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    ProductServiceImpl(final ProductRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public List<Product> list() {
        return repository.findAll();
    }

}
