package com.jciterceros.locadoracarrosjpa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OpenAPI configOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Locadora de Carros API")
                        .description("API para locação de carros")
                        .version("1.0.0")
        );
    }
}
