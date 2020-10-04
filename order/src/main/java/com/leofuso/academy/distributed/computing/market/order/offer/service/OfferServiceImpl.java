package com.leofuso.academy.distributed.computing.market.order.offer.service;

import java.util.Objects;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.leofuso.academy.distributed.computing.market.order.offer.model.Offer;
import com.leofuso.academy.distributed.computing.market.order.product.stock.ProductStockService;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;

import reactor.core.publisher.Flux;

@Service
class OfferServiceImpl implements OfferService {

    private final ProductStockService stockService;
    private final ConversionService converter;

    OfferServiceImpl(ProductStockService stockService, ConversionService conversionService) {
        this.stockService = Objects.requireNonNull(stockService);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @Override
    public Flux<Offer> list() {
        return stockService.list()
                           .filter(ProductStock::isAvailable)
                           .map(product -> {

                               final Offer offer = converter.convert(product, Offer.class);
                               Objects.requireNonNull(offer);

                               return offer;
                           });
    }
}
