package com.leofuso.academy.distributed.computing.market.order.offer.application.resource.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import com.leofuso.academy.distributed.computing.market.order.common.annotation.ConverterComponent;
import com.leofuso.academy.distributed.computing.market.order.offer.application.resource.OfferResource;
import com.leofuso.academy.distributed.computing.market.order.offer.model.Offer;
import com.leofuso.academy.distributed.computing.market.order.offer.model.SimpleOffer;

@ConverterComponent
public class OfferToOfferResource implements Converter<Offer, OfferResource> {

    @Override
    public OfferResource convert(@NonNull Offer source) {
        if (source instanceof SimpleOffer simple) {
            return new OfferResource(
                    simple.getId(),
                    simple.getName(),
                    simple.getDescription(),
                    simple.getPrice()
            );
        }
        return null;
    }
}
