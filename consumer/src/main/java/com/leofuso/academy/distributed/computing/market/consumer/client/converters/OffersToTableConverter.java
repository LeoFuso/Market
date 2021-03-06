package com.leofuso.academy.distributed.computing.market.consumer.client.converters;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.lang.NonNull;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.commons.AbstractConverter;
import com.leofuso.academy.distributed.computing.market.consumer.commons.TableFormatter;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Offer;

@Component
public final class OffersToTableConverter extends AbstractConverter<List<Offer>, Table> {

    public OffersToTableConverter(final ConfigurableConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public Table convert(@NonNull final List<Offer> source) {

        final List<Offer> sortedOffers = source
                .stream()
                .sorted(Comparator.comparingLong(Offer::getId))
                .collect(Collectors.toList());

        //the model needs to use a linked hash map
        final LinkedHashMap<String, Object> headers = new LinkedHashMap<>(Map.of(
                "id", "Id",
                "name", "Name",
                "description", "Description",
                "price", "Price"));

        final TableModel model = new BeanListTableModel<>(sortedOffers, headers);
        final TableBuilder tableBuilder = new TableBuilder(model);

        return TableFormatter.format(tableBuilder);
    }
}

