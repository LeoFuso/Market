package com.leofuso.academy.distributed.computing.market.consumer.config;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class OrderWebServiceClientConfiguration {

    private final String baseUrl;
    private final Integer connectionTimeout;
    private final Integer readTimeout;
    private final Integer writeTimeout;

    public OrderWebServiceClientConfiguration(@Value("${order.webservice.baseUrl") final String baseUrl,
                                              @Value("${order.webservice.connectionTimeout") final Integer connectionTimeout,
                                              @Value("${order.webservice.readTimeout") final Integer readTimeout,
                                              @Value("${order.webservice.writeTimeout") final Integer writeTimeout) {
        this.baseUrl = Objects.requireNonNull(baseUrl);
        this.connectionTimeout = Objects.requireNonNull(connectionTimeout);
        this.readTimeout = Objects.requireNonNull(readTimeout);
        this.writeTimeout = Objects.requireNonNull(writeTimeout);
    }

    @Bean
    public WebClient orderWebClient() {

        final HttpClient httpClient = HttpClient
                .create()
                .tcpConfiguration(tcpClient ->
                        tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                                        .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS))));

        final ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .clientConnector(connector)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
