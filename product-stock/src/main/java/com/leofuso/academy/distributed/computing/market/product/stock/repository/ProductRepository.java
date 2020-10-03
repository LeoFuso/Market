package com.leofuso.academy.distributed.computing.market.product.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leofuso.academy.distributed.computing.market.product.stock.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
