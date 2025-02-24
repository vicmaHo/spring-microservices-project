package com.vho.microservices.productservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // indico que los test se van a correr en un puerto random para evitar conflictos
class ProductServiceApplicationTests {

	@ServiceConnection //asigna dinamicamente el host y el puerto para mongodb
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	
	@LocalServerPort
	private Integer port; // inyecto el puerto en el que la aplicacion esta corriendo
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	
	static {
		mongoDBContainer.start();
	}
	
	@Test
	void shouldCreateProduct() {
		
		String requestBody = """
				{
				 "name": "Play 6",
				 "description": "Consola de videjuegos Playstation",
				 "price": 5000000
				}
				""";
		
		RestAssured.given()
			.contentType("application/json")
			.body(requestBody)
			.when()
			.post("/api/product")
			.then()
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo("Play 6"))
			.body("description", Matchers.equalTo("Consola de videjuegos Playstation"))
			.body("price", Matchers.equalTo(5000000));
			
		
	}

}
