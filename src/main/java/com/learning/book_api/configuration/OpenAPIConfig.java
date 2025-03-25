package com.learning.book_api.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API Documentation",
                description = "Spring Boot REST API Documentation of Book management API",
                version = "1.0",
                contact = @Contact(
                        name = "Giovani B. Guidetti",
                        email = "giovanibguidetti@gmail.com",
                        url = "https://www.linkedin.com/in/giovani-bueno-guidetti-950581236/"
                )
        )
)
public class OpenAPIConfig {
}
