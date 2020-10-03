package com.leofuso.academy.distributed.computing.market.product.stock.application.resource.converter;

import org.springframework.core.convert.converter.Converter;

import com.leofuso.academy.distributed.computing.market.product.stock.application.resource.ProductResource;
import com.leofuso.academy.distributed.computing.market.product.stock.common.annotation.ConverterComponent;
import com.leofuso.academy.distributed.computing.market.product.stock.domain.Product;

@ConverterComponent
public class ProductToProductResourceConverter implements Converter<Product, ProductResource> {

    @Override
    public ProductResource convert(final Product source) {
        return new ProductResource(
                source.getId(),
                source.getName(),
                source.getDescription(),
                source.getPrice(),
                source.getQuantity(),
                source.getLastModifiedDate()
        );
    }
}
