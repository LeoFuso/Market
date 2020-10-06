package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.converters;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Cart;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Item;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.CartResource;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.ItemResource;

import reactor.util.function.Tuple2;

@Component
public class CartResourceAndItemsResourceToCartConverter extends AbstractConverter<Tuple2<CartResource, List<ItemResource>>, Cart> {

    private final ConversionService converter;

    public CartResourceAndItemsResourceToCartConverter(final ConfigurableConversionService configurableConversionService,
                                                       final ConversionService conversionService) {
        super(configurableConversionService);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @Override
    public Cart convert(final Tuple2<CartResource, List<ItemResource>> source) {

        final CartResource cartResource = source.getT1();
        final List<ItemResource> itemsResource = source.getT2();

        final Set<Item> items = itemsResource
                        .stream()
                        .map(r -> this.converter.convert(r, Item.class))
                        .collect(Collectors.toSet());

        return new Cart(
                cartResource.getId(),
                items,
                cartResource.getState());

    }
}
