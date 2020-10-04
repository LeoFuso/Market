package com.leofuso.academy.distributed.computing.market.product.stock.service;

import java.util.List;
import java.util.Optional;

import com.leofuso.academy.distributed.computing.market.product.stock.domain.Product;

public interface ProductService {

    List<Product> list();

    Optional<Product> findOne(Long id);
}
