package com.example.SuperMarket;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class ProductTest {
	
	@BeforeAll
	public void setUp()
	{
		RestAssured.baseURI="http://localhost:8080/api/products";
	}
	@Test
	public void testProductCrudOperations() {
		
	
	Response createResponse = RestAssured.given()
			.contentType(ContentType.JSON)
			.body("{\"id\":2,\"name\":\"Mobile\",\"category\":\"Electronics\",\"price\":25000.0,\"quantity\":15}")
			.when()
			.post()
			.then()
			.statusCode(200)
			.body("name",equalTo("Mobile"))
			.extract().response();
	
	Long productId = createResponse.jsonPath().getLong("id");
	System.out.println(" Created Product ID: " + productId);
	
	
	
	
			given()
				.pathParam("id", productId)
			.when()
			   .get("/{id}")
			.then()
			    .statusCode(200)
			    .body("id", equalTo(productId.intValue()));
	
	
	
//			when()
//				.get()
//			.then()
//				.statusCode(200)
//				.body("size",greaterThan(0));
			
			
//			given()
//				.pathParam("id", productId)
//			.when()
//	           .get("/{id}")
//	        .then()
//	            .statusCode(200)
//	            .body("id", equalTo(productId.intValue()));
			
			
Response updateResponse = given()
					      	.contentType(ContentType.JSON)
					        .pathParam("id", productId)
					        .body("{\"name\":\"Laptop Pro\",\"category\":\"Electronics\",\"price\":85000.0,\"quantity\":12}")
					     .when()
					     	.put("/{id}")
			
					     .then()
					      	.statusCode(anyOf(is(200), is(404)))
						 	.body("name" , equalTo("Laptop Pro"))
						 	.extract().response();
Long updateProductId = updateResponse.jsonPath().getLong("id");
System.out.println("updateresponse : " +updateResponse);



						given()
						.pathParam("id", updateProductId)
						.when()
						.get("/{id}")
						.then()
						.statusCode(200)
						.body("id", equalTo(updateProductId.intValue()));
			 
			 
			 when()
				.get()
			.then()
				.statusCode(200)
				.body("size",greaterThan(0));
		       
			
			
			given()
				.pathParam("id",productId)
			.when()
				.delete("/{id}")
			.then()
				.statusCode(200);
			
			
			given()
			.pathParam("id",updateProductId)
		.when()
			.delete("/{id}")
		.then()
			.statusCode(200);
			
			
			
	}
			
			

}
