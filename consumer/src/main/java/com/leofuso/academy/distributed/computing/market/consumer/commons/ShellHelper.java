package com.leofuso.academy.distributed.computing.market.consumer.commons;

import org.jline.terminal.Terminal;

public class ShellHelper {

    private Terminal terminal;

    public ShellHelper(Terminal terminal) {
        this.terminal = terminal;
    }

    public void print(String message) {
        terminal.writer().println(message);
        terminal.flush();
    }
}
