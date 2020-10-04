package com.leofuso.academy.distributed.computing.market.consumer.config;

import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import com.leofuso.academy.distributed.computing.market.consumer.commons.ShellHelper;

@Configuration
public class SpringShellConfiguration {

    @Bean
    public ShellHelper shellHelper(@Lazy Terminal terminal) {
        return new ShellHelper(terminal);
    }

}
