package net.d4y2k.profileservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "User Profile API",
                description = "API which implements basic user profile methods",
                contact = @Contact(
                        name = "d4y2k",
                        email = "damirr765@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
