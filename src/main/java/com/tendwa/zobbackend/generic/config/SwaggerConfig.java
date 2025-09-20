package com.tendwa.zobbackend.generic.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Zob Incident Tracking System",
                version = "v0.1",
                description = "Observation and Incident tracking system",
                contact = @Contact(name = "Support", email = "tiruskhamasi@gmail.com")
        )
)

public class SwaggerConfig {
}
