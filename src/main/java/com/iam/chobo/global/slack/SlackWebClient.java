package com.iam.chobo.global.slack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class SlackWebClient {
    @Value("${slack.webhook-url.info}")
    private String infoHook;

    @Bean
    public Builder webClient() {
        return WebClient.builder().baseUrl(infoHook);
    }
     
}
