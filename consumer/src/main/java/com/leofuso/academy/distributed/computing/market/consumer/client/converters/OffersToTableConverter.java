package com.leofuso.academy.distributed.computing.market.consumer.client.converters;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.commons.TableFormatter;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.model.Offer;

@Component
public final class OffersToTableConverter implements Converter<List<Offer>, Table> {

    @Override
    public Table convert(@NonNull final List<Offer> source) {

        //the model needs to use a linked hash map
        final LinkedHashMap<String, Object> headers = new LinkedHashMap<>(Map.of(
                "id", "Id",
                "name", "Name",
                "description", "Description",
                "price", "Price"));

        final TableModel model = new BeanListTableModel<>(source, headers);
        final TableBuilder tableBuilder = new TableBuilder(model);

        return TableFormatter.format(tableBuilder);
    }
}

