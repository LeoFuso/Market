package com.leofuso.academy.distributed.computing.market.consumer.commons;

import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.SimpleHorizontalAligner;
import org.springframework.shell.table.SimpleVerticalAligner;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;

public class TableFormatter {

    public static Table format(final TableBuilder tableBuilder) {

        return tableBuilder.on((row, column, m) -> true)
                .addAligner(SimpleVerticalAligner.middle)
                .addAligner(SimpleHorizontalAligner.center)
                .and()
                .addFullBorder(BorderStyle.fancy_light)
                .build();
    }
}
