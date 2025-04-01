package com.vho.microservices.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.vho.microservices.order.client.InventoryClient;

@Configuration
public class RestClientConfig {

	@Value("${inventory.url}")
	private String inventoryServiceUrl;
	
	@Bean
	public InventoryClient inventoryClient() {
		RestClient client = RestClient.builder()
				.baseUrl(inventoryServiceUrl)
				.build();
		var restClientAdapter = RestClientAdapter.create(client);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(InventoryClient.class);
	}
}
