package com.leofuso.academy.distributed.computing.market.consumer.client.converters;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;
import org.springframework.stereotype.Component;
import com.leofuso.academy.distributed.computing.market.consumer.commons.AbstractConverter;
import com.leofuso.academy.distributed.computing.market.consumer.commons.TableFormatter;
import com.leofuso.academy.distributed.computing.market.consumer.shopping.api.model.Item;

@Component
public class ItemsToTableConverter extends AbstractConverter<Set<Item>, Table> {

    public ItemsToTableConverter(final ConfigurableConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public Table convert(final Set<Item> source) {

        final List<Item> sortedItems = source
                .stream()
                .sorted(Comparator.comparingLong(a -> a.getOffer().getId()))
                .collect(Collectors.toList());

        //the model needs to use a linked hash map
        final LinkedHashMap<String, Object> headers = new LinkedHashMap<>(Map.of(
                "offer.id", "Product Id",
                "offer.name", "Product Name",
                "offer.description", "Product Description",
                "offer.price", "Product Price",
                "quantity", "Quantity",
                "subTotal", "Subtotal"));

        final TableModel model = new BeanListTableModel<>(source, headers);
        final TableBuilder tableBuilder = new TableBuilder(model);

        return TableFormatter.format(tableBuilder);
    }
}
