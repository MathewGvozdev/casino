package com.mgvozdev.casino.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Casino management tool",
                description = """
                        It is a prototype of the backend offline casino management tool.
                        Data consist of players and their sessions, tables and their sessions, chips, users (all manager staff),
                        dealers, rewards, reports, roles and authorities.
                        """,
                version = "1.0.0",
                contact = @Contact(
                        name = "Mathew Gvozdev",
                        email = "mathew15gen@gmail.com",
                        url = "https://github.com/MathewGvozdev"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(Type.HTTP)
                                .scheme("basic")));
    }
}
