package com.leofuso.academy.distributed.computing.market.product.stock.service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.product.stock.application.action.RemoveStockProductRequest;
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

    @Override
    public Optional<Product> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void process(RemoveStockProductRequest request) {
        request.getItems()
               .parallelStream()
               .forEach(item -> {

                   final Long productId = item.getProductId();
                   final Product product = repository.findById(productId)
                                                     .orElseThrow();

                   final Integer actualQuantity = product.getQuantity();
                   final Integer quantityToBeRemoved = item.getQuantity();
                   final int newQuantity = actualQuantity - quantityToBeRemoved;
                   if (newQuantity < 0) {
                       throw new RuntimeException("Invalid quantity");
                   }

                   product.setQuantity(newQuantity);

               });
    }
}
