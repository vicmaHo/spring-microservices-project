package com.vho.microservices.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vho.microservices.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
