package edu.ph.myschoolportal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI schoolManagementSystemOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("School Management System API")
                        .description("School Management System API v1.0")
                        .version("v1.0"));
    }
}
