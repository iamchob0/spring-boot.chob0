package com.iam.chobo.global.slack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;

@Slf4j
@Component
@AllArgsConstructor
public class SlackSenderManager {
    
    @Autowired
    private Builder webClient;
    
    public void send(Object object) {
        log.info("slack : {}", object.toString());
        webClient.build().post().uri("").bodyValue(object).retrieve().bodyToMono(String.class).block();
    }

}