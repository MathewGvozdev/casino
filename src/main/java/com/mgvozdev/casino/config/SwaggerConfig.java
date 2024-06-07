package com.mgvozdev.casino.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
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
                        url = "https://github.com/AnnaLeonova1808"
                )
        )
)
@Configuration
public class SwaggerConfig {
}
