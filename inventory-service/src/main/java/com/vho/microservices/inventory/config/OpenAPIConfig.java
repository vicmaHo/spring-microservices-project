package com.vho.microservices.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI inventoryServiceAPI() {
		return new OpenAPI()
				.info(new Info().title("Inventory Service API")
						.description("REST API for inventory service")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0")))
				.externalDocs(new ExternalDocumentation().description("Inventory service wiki documentation")
						.url("http://inventory-service-test-url.com/docs"));
	}
}
