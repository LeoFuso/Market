package com.leofuso.academy.distributed.computing.market.consumer.client.converters;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.SimpleHorizontalAligner;
import org.springframework.shell.table.SimpleVerticalAligner;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.offer.api.Offer;

@Component
public final class OffersToTableConverter implements Converter<Set<Offer>, Table> {

    @Override
    public Table convert(@NonNull final Set<Offer> source) {

        final List<Offer> restrictionList =
                source.stream()
                        .sorted(Comparator.comparingLong(Offer::getId))
                        .collect(Collectors.toList());

        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("name", "Name");
        headers.put("description", "Description");
        headers.put("price", "Price");
        TableModel model = new BeanListTableModel<>(source, headers);

        TableBuilder tableBuilder = new TableBuilder(model);
        return tableBuilder.on((row, column, m) -> true)
                .addAligner(SimpleVerticalAligner.middle)
                .addAligner(SimpleHorizontalAligner.center)
                .and()
                .addFullBorder(BorderStyle.fancy_light)
                .build();
    }
}

