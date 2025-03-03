package com.vho.microservices.inventory.service;

import org.springframework.stereotype.Service;

import com.vho.microservices.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	
	public boolean isInStock(String skuCode, Integer quantity) {
		
		return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
	}
	
}
