package com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.leofuso.academy.distributed.computing.market.order.common.annotation.ConverterComponent;
import com.leofuso.academy.distributed.computing.market.order.offer.model.Offer;
import com.leofuso.academy.distributed.computing.market.order.offer.model.SimpleOffer;
import com.leofuso.academy.distributed.computing.market.order.product.stock.integration.model.ProductStock;

@ConverterComponent
public class ProductStockToOffer implements Converter<ProductStock, Offer> {

    @Override
    public Offer convert(@NonNull ProductStock source) {
        return new SimpleOffer(
                source.getId(),
                source.getName(),
                source.getDescription(),
                source.getPrice()
        );
    }
}
