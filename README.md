# **Microservices Architecture with Spring Boot**

A practice project implementing a **microservices architecture** using **Spring Boot**, featuring an **API Gateway**, multiple services, and security.

## **Architecture Overview**

This project follows a **microservices-based** architecture:

- **API Gateway** → Handles authentication, routes requests to microservices (Spring Cloud Gateway).
- **Product Service** → Manages products.
- **Inventory Service** → Manages stock levels and product availability.
- **Order Service** → Handles customer orders and integrates with the Inventory Service.

## **Tech Stack**

- **Backend:** Java 21, Spring Boot 3, Spring Cloud
- **Database:** PostgreSQL / MongoDB
