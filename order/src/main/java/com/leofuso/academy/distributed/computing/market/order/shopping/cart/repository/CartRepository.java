package com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {}
