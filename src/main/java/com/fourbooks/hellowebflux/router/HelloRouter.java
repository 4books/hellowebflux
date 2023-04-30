package com.fourbooks.hellowebflux.router;

import com.fourbooks.hellowebflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class HelloRouter {
    private final HelloHandler helloHandler;

    public HelloRouter(HelloHandler helloHandler) {
        this.helloHandler = helloHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(GET("/hello"), this::handleHello)
                .andRoute(GET("/hello/{id}"), this::handleHelloWithId)
                .andRoute(GET("/numbers"), this::handleNumbers)
                .andRoute(POST("/lotto"), this::handleRandomNumbers);
    }

    private Mono<ServerResponse> handleHello(ServerRequest req) {
        return ServerResponse.ok().body(helloHandler.hello(), String.class);
    }

    private Mono<ServerResponse> handleHelloWithId(ServerRequest req) {
        String id = req.pathVariable("id");
        return ServerResponse.ok().body(helloHandler.helloWithId(id), String.class);
    }

    private Mono<ServerResponse> handleNumbers(ServerRequest req) {
        Flux<Map<String, Integer>> numbers = Flux.fromStream(
                IntStream.rangeClosed(1, 10).boxed().map(i -> Map.of("number", i))
        );
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(numbers, Map.class);
    }

    private Mono<ServerResponse> handleRandomNumbers(ServerRequest req) {
        List<Integer> numbers = new ArrayList<>(45);
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> randomNumbers = numbers.subList(0, 6);
        Map<String, List<Integer>> responseBody = Map.of("lotto", randomNumbers);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(responseBody);
    }
}
