package com.vho.microservices.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI orderSerivceAPI() {
		return new OpenAPI()
				.info(new Info().title("Order Service API")
						.description("REST API for Order service")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0")))
				.externalDocs(new ExternalDocumentation().description("Order service wiki documentation")
						.url("http://order-service-test-url.com/docs"));
	}
}
