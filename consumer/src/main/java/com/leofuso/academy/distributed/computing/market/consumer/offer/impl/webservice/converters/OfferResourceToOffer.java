package com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Offer;
import com.leofuso.academy.distributed.computing.market.consumer.offer.impl.webservice.resources.OfferResource;

@Component
public class OfferResourceToOffer implements Converter<OfferResource, Offer> {

    @Override
    public Offer convert(@NonNull final OfferResource source) {

        return new Offer(
                source.getId(),
                source.getName(),
                source.getDescription(),
                source.getPrice());
    }
}
