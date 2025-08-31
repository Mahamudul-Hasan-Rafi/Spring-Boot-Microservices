package com.eazybank.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController {

    @GetMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An error occurred. Please wait and contact support");
    }
}
