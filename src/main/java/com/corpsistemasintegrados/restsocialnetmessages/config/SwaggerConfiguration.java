package com.corpsistemasintegrados.restsocialnetmessages.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * <h1>Swagger documentation</h1>
 * Configuration for security service
 *
 * @author  Diego Mendez
 * @since 0.5
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "REST API CHAT MESSAGES",
                "This API creates a conversation history from different platforms.",
                "0.5.0",
                "Copyright Corporacion Sistemas Integrados S.A.",
                new Contact("Información", "www.sisintegrados.com", "info@sisintegrados.com"),
                "License of API Corporacion Sistemas Integrados S.A.", "www.sisintegrados.com", Collections.emptyList());
    }
}

