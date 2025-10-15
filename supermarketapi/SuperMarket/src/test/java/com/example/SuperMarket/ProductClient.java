package com.example.SuperMarket;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://localhost:8080/api/products";
		
		Response postResponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body("{\"name\":\"Rice\",\"category\":\"Groceries\",\"price\":75000.0,\"quantity\":10}")
				.post();
		
		System.out.println("POST RESPONSE : " +postResponse.asString());
		
		Long productId = postResponse.jsonPath().getLong("id");
		System.out.println("Post Response : " +productId);
		
		
		
//		Response getResponse = RestAssured.get();
//		System.out.println("GET RESPONSE : " +getResponse.asString());
//		
		
		Response getByIdResponse = RestAssured.given()
				.pathParam("id", productId)
				.get("/{id}");
		System.out.println("GET BY ID RESPONSE : " +getByIdResponse.asString());
		
		Response putResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id",productId) 
                .body("{\"name\":\"sugar\",\"category\":\"Groceries\",\"price\":85000.0,\"quantity\":20}")
                .put("/{id}");
        System.out.println("PUT RESPONSE : " + putResponse.asString());
        
        
        Response getUpdatedResponse = RestAssured.given()
				.pathParam("id", productId)
				.get("/{id}");
		System.out.println("GET UPDATED RESPONSE : " +getUpdatedResponse.asString());
		
		

		Response getResponse = RestAssured.get();
		System.out.println("GET RESPONSE : " +getResponse.asString());
		
		Response deleteByIdResponse = RestAssured.given()
				.pathParam("id", productId)
				.delete("/{id}");
		System.out.println("DELETE BY ID RESPONSE : " +deleteByIdResponse.asString());
		
		
		 Response verifyDeletedResponse = RestAssured.given()
	                .pathParam("id", productId)
	                .get("/{id}");

	        System.out.println("VERIFY DELETED PRODUCT RESPONSE : " + verifyDeletedResponse.getStatusCode());
	        
	        
	        
	        Response getAllAfterDelete = RestAssured.get();
	        System.out.println("GET ALL AFTER DELETE RESPONSE : " + getAllAfterDelete.asString());

	    }



}


