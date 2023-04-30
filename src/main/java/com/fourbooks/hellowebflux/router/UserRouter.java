package com.fourbooks.hellowebflux.router;

import com.fourbooks.hellowebflux.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class UserRouter {
    private final UserService userService;

    public UserRouter(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return RouterFunctions.route(POST("/users/{id}"), this::handleFindUserById);
    }

    private Mono<ServerResponse> handleFindUserById(ServerRequest req) {
        Long id = Long.valueOf(req.pathVariable("id"));
        return userService.findById(id)
                .map(user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(user))
                .orElse(ServerResponse.status(HttpStatus.NOT_FOUND).build());
    }
}
