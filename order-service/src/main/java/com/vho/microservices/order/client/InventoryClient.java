package com.vho.microservices.order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


public interface InventoryClient {
	
	Logger log = LoggerFactory.getLogger(InventoryClient.class);
	
	@GetExchange("/api/inventory")
	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod") // el nombre debe ser el mismo de la instancia que se define en propierties
	@Retry(name = "inventory")
	boolean isInStock(@RequestParam(name = "skuCode") String skuCode, @RequestParam(name = "quantity") Integer quantity);
	
	
	// proveemos el metodo de fallback, definimos el mensaje
	default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
		log.info("No se puede acceder al inventario para el skucode {}, razon del erro: {}", code, throwable.getMessage());
		return false;
	}

}
