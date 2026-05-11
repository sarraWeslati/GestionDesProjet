package com.project.project_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server server = new Server();

        server.setUrl("https://project-backend-89530374547.us-central1.run.app");

        server.setDescription("Production Server");

        return new OpenAPI()
                .servers(List.of(server));
    }
}