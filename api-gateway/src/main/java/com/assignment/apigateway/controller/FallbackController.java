package com.assignment.apigateway.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @GetMapping("/fallback/default")
    public Mono<String> productFallback() {
        return Mono.just("Service is NOT available.");
    }
}
