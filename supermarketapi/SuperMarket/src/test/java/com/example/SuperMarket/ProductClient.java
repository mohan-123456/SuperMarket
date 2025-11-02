package com.example.SuperMarket;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://localhost:8080/api/products";
		//Post Response
		Response postResponse = RestAssured.given()
				.contentType(ContentType.JSON)
				.body("{\"name\":\"Rice\",\"category\":\"Groceries\",\"price\":75000.0,\"quantity\":10}")
				.post();
		
		System.out.println("POST RESPONSE : " +postResponse.asString());
		//Extract the id from post response
		Long productId = postResponse.jsonPath().getLong("id");
		System.out.println("Post Response : " +productId);
		
		
		
//		Response getResponse = RestAssured.get();
//		System.out.println("GET RESPONSE : " +getResponse.asString());
//		
		//Get the produuct from extracted id
		Response getByIdResponse = RestAssured.given()
				.pathParam("id", productId)
				.get("/{id}");
		System.out.println("GET BY ID RESPONSE : " +getByIdResponse.asString());
		//Update the product with the above id
		Response putResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id",productId) 
                .body("{\"name\":\"sugar\",\"category\":\"Groceries\",\"price\":85000.0,\"quantity\":20}")
                .put("/{id}");
        System.out.println("PUT RESPONSE : " + putResponse.asString());
        //Extract the id of updated product
        Long updatedProductId = putResponse.jsonPath().getLong("id");
        System.out.println("UPDATED PRODUCT ID : " +updatedProductId);
        
        //Get the updaed product by id
        Response getUpdatedResponse = RestAssured.given()
				.pathParam("id", updatedProductId)
				.get("/{id}");
		System.out.println("GET UPDATED RESPONSE : " +getUpdatedResponse.asString());
		
		
		//Get all the products
		Response getResponse = RestAssured.get();
		System.out.println("GET RESPONSE : " +getResponse.asString());
		
		//Delete the product with productid
		Response deleteByIdResponse = RestAssured.given()
				.pathParam("id", productId)
				.delete("/{id}");
		System.out.println("DELETE BY ID RESPONSE : " +deleteByIdResponse.asString());

		//Delete the updated productid
		Response deleteUpdateResponse = RestAssured.given()
				.pathParam("id", updatedProductId)
				.delete("/{id}");
		System.out.println("DELETE BY UPDATE PRODUCT ID RESPONSE : " +deleteByIdResponse.asString());
		
		//check the productid whether it is deleted
		 Response verifyDeletedResponse = RestAssured.given()
	                .pathParam("id", productId)
	                .get("/{id}");
		 System.out.println("VERIFY DELETED PRODUCT RESPONSE : " + verifyDeletedResponse.getStatusCode());

		//check the updatedproductid whether it is deleted
		 Response verifyDeletedUpadtedResponse = RestAssured.given()
	                .pathParam("id", updatedProductId)
	                .get("/{id}");
		 System.out.println("VERIFY DELETED UPDATED PRODUCT RESPONSE : " + verifyDeletedUpadtedResponse.getStatusCode());
	        
	         //Get all the products
	        Response getAllAfterDelete = RestAssured.get();
	        System.out.println("GET ALL AFTER DELETE RESPONSE : " + getAllAfterDelete.asString());

	    }



}





