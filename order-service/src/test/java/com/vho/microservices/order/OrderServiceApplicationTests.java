package com.vho.microservices.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MySQLContainer;

import com.vho.microservices.order.stubs.InventoryClientStub;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) // anotacion para las clases de testeo que van a iniciar un server de wiremock (puerto random = 0)
@TestPropertySource(locations = "classpath:application.properties")
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void shouldCreateOrder() {
        String submitOrderJson = """
                {
                 "skuCode": "pen", 
                 "price": 2000000,
                 "quantity": 1
                }
                """;
        
        InventoryClientStub.stubInventoryCall("pen", 1);

        var responseBodyString = RestAssured.given()
                .contentType("application/json")
                .body(submitOrderJson)
                .when()
                .post("/api/order")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        assertEquals("Pedido realizado con éxito", responseBodyString);
    }
}
