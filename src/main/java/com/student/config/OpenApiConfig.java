package com.student.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studentManagementOpenAPI() {

        Contact contact = new Contact();
        contact.setName("Rehan Shaikh");

        Info info = new Info()
                .title("Student Management System API")
                .version("1.0")
                .description(
                        "REST API for managing students, courses and enrollments."
                )
                .contact(contact);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement =
                new SecurityRequirement()
                        .addList("Bearer Authentication");

        Components components = new Components()
                .addSecuritySchemes(
                        "Bearer Authentication",
                        securityScheme
                );

        return new OpenAPI()
                .info(info)
                .components(components)
                .addSecurityItem(securityRequirement);
    }
}