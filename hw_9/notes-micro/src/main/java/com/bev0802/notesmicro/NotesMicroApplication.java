package com.bev0802.notesmicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotesMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesMicroApplication.class, args);

    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("web-client",r->r.path("/wesService/**")
                        .uri("http://localhost:8081/"))
                .route("rest-client",r->r.path("/restService/**")
                        .uri("http://localhost:8082/")).build();}
}
