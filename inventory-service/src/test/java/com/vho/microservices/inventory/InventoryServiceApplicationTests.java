package com.vho.microservices.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

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
    void shouldCheckInventoryAvailability() {
        System.out.println("Iniciando test de disponibilidad de inventario...");

        var responseBodyString = RestAssured.given()
                .contentType("application/json")
                .queryParam("skuCode", "pencil")
                .queryParam("quantity", 1)
                .when()
                .get("/api/inventory")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        System.out.println("Respuesta del servidor: " + responseBodyString);

        assertEquals("true", responseBodyString); 

        System.out.println("Test de disponibilidad de inventario finalizado con éxito.");
    }
}
