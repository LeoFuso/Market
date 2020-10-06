package com.leofuso.academy.distributed.computing.market.consumer.commons;

import java.util.Objects;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;

import javax.annotation.PostConstruct;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    private final ConfigurableConversionService conversionService;

    protected AbstractConverter(final ConfigurableConversionService conversionService) {
        this.conversionService = Objects.requireNonNull(conversionService);
    }

    @PostConstruct
    private void postConstruct() {
        this.conversionService.addConverter(this);
    }

    protected final <C> C convert(final Object source, final Class<C> clazz) {
        return this.conversionService.convert(source, clazz);
    }
}
