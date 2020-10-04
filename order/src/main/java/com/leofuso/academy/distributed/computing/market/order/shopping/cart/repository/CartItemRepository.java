package com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {}
