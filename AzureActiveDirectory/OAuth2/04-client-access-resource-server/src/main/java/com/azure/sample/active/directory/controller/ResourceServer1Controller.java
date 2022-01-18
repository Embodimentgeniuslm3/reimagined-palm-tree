package com.azure.sample.active.directory.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
public class ResourceServer1Controller {

    private final WebClient webClient;

    public ResourceServer1Controller (WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/client/resource-server-1/hello")
    public String resourceServer1(@RegisteredOAuth2AuthorizedClient("client-1") OAuth2AuthorizedClient client1) {
        return webClient
            .get()
            .uri("http://localhost:8081/")
            .attributes(oauth2AuthorizedClient(client1))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}