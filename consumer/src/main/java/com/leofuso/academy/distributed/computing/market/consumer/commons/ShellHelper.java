package com.leofuso.academy.distributed.computing.market.consumer.commons;

import org.jline.terminal.Terminal;

public class ShellHelper {

    private final Terminal terminal;

    public ShellHelper(final Terminal terminal) {
        this.terminal = terminal;
    }

    public void print(final String message) {
        this.terminal.writer().println(message);
        this.terminal.flush();
    }
}
