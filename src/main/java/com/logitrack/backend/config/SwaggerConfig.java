package com.logitrack.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "LogiTrack API",
                version = "1.0",
                description = "API para la gestión logística y auditoría de bodegas"
        ),
        security = @SecurityRequirement(name = "bearerAuth") // Aplica seguridad a toda la API
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Ingresa tu token JWT aquí para autenticarte",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}