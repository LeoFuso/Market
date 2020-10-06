package com.leofuso.academy.distributed.computing.market.consumer.shopping.impl.webservice.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;

import javax.annotation.PostConstruct;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ConfigurableConversionService conversionService;

    @PostConstruct
    private void postConstruct() {
        conversionService.addConverter(this);
    }

    protected final <C> C convert(final Object o, final Class<C> clazz) {
        return conversionService.convert(o, clazz);
    }
}
