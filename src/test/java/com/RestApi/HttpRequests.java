package com.RestApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HttpRequests {

	int id;
	
	@Test(priority = 1)
	void getusers() {

		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2)
	void create() {

		HashMap data=new HashMap();
		data.put("name", "krishna");
		data.put("location", "chennai");
		
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		   .post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
	//	.then()
		//  .statusCode(201)
		  //.log().all();
		

	}
	
	@Test(priority = 3,dependsOnMethods = {"create"} )
	void put() {
		HashMap data=new HashMap();
		data.put("name", "bharath");
		data.put("location","bangalore");
		
		given()
		  .contentType("application/json")
		  .body(data)
		  
		.when()
		   .put("https://reqres.in/api/users/"+id)
		 
		.then()
		  .statusCode(200)
		  .log().all();
		
	}
	
	
	
	
	
	
	
} 	