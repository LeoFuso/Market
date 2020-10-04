package com.leofuso.academy.distributed.computing.market.order.shopping.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leofuso.academy.distributed.computing.market.order.shopping.cart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByCartId(Long cartId);

    Optional<CartItem> findByCartIdAndProductReference(Long cartId, Long productReference);

}
