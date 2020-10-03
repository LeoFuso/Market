package com.leofuso.academy.distributed.computing.market.consumer;

import org.jline.reader.impl.history.DefaultHistory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ConsumerApplication {

    public static void main(final String[] args) {

        final String[] disabledCommands = {
                "--spring.shell.command.history.enabled=false"
        };

        final String[] fullArgs = StringUtils.concatenateStringArrays(args, disabledCommands);
        SpringApplication.run(ConsumerApplication.class, fullArgs);
    }

    @Component
    public static class NoSaveHistory extends DefaultHistory {
        @Override
        public void save() {
            /* This disables the REPL Logging behavior */
        }
    }
}
