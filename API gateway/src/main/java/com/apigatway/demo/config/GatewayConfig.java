package com.apigatway.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableHystrix
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("demo", r -> r.path("/api/v1/demo")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8081"))

                .route("login", r -> r.path("api/v1/auth/login")
//                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8080"))
                .build();
    }

}