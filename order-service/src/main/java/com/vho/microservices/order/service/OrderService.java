package com.vho.microservices.order.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vho.microservices.order.client.InventoryClient;
import com.vho.microservices.order.dto.OrderRequest;
import com.vho.microservices.order.model.Order;
import com.vho.microservices.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final InventoryClient inventoryClient;
	
	
	
	public void placeOrder(OrderRequest orderRequest) {
		
		// hago uso de la funcion definida en la interface, openfeign se encarga de la implementacion
		var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
		
		//verifico que el producto este disponible
		if (isProductInStock) {
			Order order = new Order();
			order.setOrderNumber(UUID.randomUUID().toString());
			order.setPrice(orderRequest.price());
			order.setSkuCode(orderRequest.skuCode());
			order.setQuantity(orderRequest.quantity());
			
			orderRepository.save(order);
		}else {
			throw new RuntimeException("Producto con codigo " + orderRequest.skuCode() + " no esta disponible.");
		}

	}
}
