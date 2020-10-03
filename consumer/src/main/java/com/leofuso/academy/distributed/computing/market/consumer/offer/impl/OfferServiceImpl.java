package com.leofuso.academy.distributed.computing.market.consumer.offer.impl;

import java.util.Collections;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.Offer;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

    @Override
    public Set<Offer> list() {
        return Collections.emptySet();
    }
}
