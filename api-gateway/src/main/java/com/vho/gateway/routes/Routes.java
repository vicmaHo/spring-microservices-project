package com.vho.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

	
	@Bean
	public RouterFunction<ServerResponse> productServiceRoute(){
		// para esta ocasion usamos functional endpoints, una forma distinta de definir y manejar endpoints http
		return GatewayRouterFunctions.route("product_service")
				.route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080")) // definimos un path y redirigimos a el microservicio
				.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> orderServiceRoute(){
		return GatewayRouterFunctions.route("order_service")
				.route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
				.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> inventoryService(){
		return GatewayRouterFunctions.route("inventory_service")
				.route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
				.build();
	}
	
	
}
