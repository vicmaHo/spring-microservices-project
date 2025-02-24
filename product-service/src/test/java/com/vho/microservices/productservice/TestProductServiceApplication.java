package com.vho.microservices.productservice;

import org.springframework.boot.SpringApplication;

import com.vho.microservices.productservice.ProductServiceApplication;

public class TestProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
