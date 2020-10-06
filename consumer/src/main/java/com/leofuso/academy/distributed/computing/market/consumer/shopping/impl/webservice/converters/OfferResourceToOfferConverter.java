package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.converters;

import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Offer;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.resources.OfferResource;

@Component
public class OfferResourceToOfferConverter extends AbstractConverter<OfferResource, Offer> {

    public OfferResourceToOfferConverter(final ConfigurableConversionService configurableConversionService) {
        super(configurableConversionService);
    }

    @Override
    public Offer convert(@NonNull final OfferResource source) {

        return new Offer(
                source.getId(),
                source.getName(),
                source.getDescription(),
                source.getPrice());
    }
}
