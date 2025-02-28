package com.vho.microservices.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vho.microservices.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
