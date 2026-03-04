package com.dws.bands.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${band.api.url}")
    private String bandApiUrl;

    @Bean
    public WebClient bandsWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(bandApiUrl)
                .build();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
