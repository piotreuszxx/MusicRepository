package org.example.musicappgateway;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("song-service", r -> r
                        .path("/api/songs", "/api/songs/**")
                        .uri("http://localhost:8081"))

                .route("artist-service", r -> r
                        .path("/api/artists", "/api/artists/**")
                        .uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
        corsConfig.setAllowedHeaders(List.of("Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
