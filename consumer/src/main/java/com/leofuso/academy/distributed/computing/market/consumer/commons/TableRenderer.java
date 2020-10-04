package com.leofuso.academy.distributed.computing.market.consumer.commons;

import org.springframework.shell.table.Table;

public class TableRenderer {

    private static final Integer TERMINAL_WIDTH = 100;

    public static String render (final Table table) {
        return table.render(TERMINAL_WIDTH);
    }

}
