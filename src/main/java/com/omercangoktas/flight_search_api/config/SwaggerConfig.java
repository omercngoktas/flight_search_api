package com.omercangoktas.flight_search_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("NAME OF SERVICE")
                        .description("API Endpoint Decoration")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Dev-Team")
                                .url("https://www.dev-team.com/")
                                .email("dev-team@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}