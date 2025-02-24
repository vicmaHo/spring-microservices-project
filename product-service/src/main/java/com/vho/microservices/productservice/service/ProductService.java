package com.vho.microservices.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vho.microservices.productservice.dto.ProductRequest;
import com.vho.microservices.productservice.dto.ProductResponse;
import com.vho.microservices.productservice.model.Product;
import com.vho.microservices.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();
		productRepository.save(product);
		log.info("Producto creado con exito");
		
		ProductResponse response = new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
		return response;
	}

	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll() // mapeo con un flujo de datos para transformar en dto de respuesta
				.stream()
				.map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
				.toList();
	}
	
}
