package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.converters;

import java.util.Objects;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Item;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Offer;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.ItemResource;

@Component
public class ItemResourceToItemConverter extends AbstractConverter<ItemResource, Item> {

    private final ConversionService converter;

    public ItemResourceToItemConverter(final ConfigurableConversionService configurableConversionService,
                                       final ConversionService conversionService) {
        super(configurableConversionService);
        this.converter = Objects.requireNonNull(conversionService);
    }

    @Override
    public Item convert(@NonNull final ItemResource source) {
        final Offer offer = this.converter.convert(source.getOffer(), Offer.class);
        return new Item(offer, source.getQuantity(), source.getSubTotal());
    }
}
