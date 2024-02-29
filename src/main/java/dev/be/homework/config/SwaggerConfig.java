package dev.be.homework.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI swaggerApi() {

        return new OpenAPI().components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info().title("switchwon").description("codetest").version("v1");
    }
}
