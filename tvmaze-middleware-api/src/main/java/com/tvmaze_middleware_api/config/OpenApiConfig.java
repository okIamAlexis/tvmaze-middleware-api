package com.tvmaze_middleware_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tvMazeMiddlewareOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("TVMaze Middleware API")
                        .description("API middleware sobre los servicios de TVMaze, con cache en MongoDB "
                                + "(y una capa adicional en memoria con Caffeine) y comentarios/calificaciones por show.")
                        .version("v1"));
    }
}
