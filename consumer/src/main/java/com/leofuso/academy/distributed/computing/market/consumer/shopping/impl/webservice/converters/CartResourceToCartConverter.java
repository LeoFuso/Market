package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.converters;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Item;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.CartResource;

@Component
public class CartResourceToCartConverter implements Converter<CartResource, Cart> {

    private final ConversionService converter;

    public CartResourceToCartConverter(final ConversionService webFluxConversionService) {
        this.converter = Objects.requireNonNull(webFluxConversionService);
    }

    @Override
    public Cart convert(@NonNull final CartResource source) {

        final Set<Item> items = source
                .getItems()
                .stream()
                .map(resource -> converter.convert(resource, Item.class))
                .collect(Collectors.toSet());

        return new Cart(source.getId(), source.getPrice(), items);
    }
}
