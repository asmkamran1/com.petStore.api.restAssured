package org.chase.api.automation;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIAutomationDemo extends Payloads {

	public static void main(String[] args) {
//		given()
//		when()
//		then()
//		Payloads obj = new Payloads();
		RestAssured.baseURI = "https://petstore.swagger.io/v2";

		Response response = given().log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").body(addPet_payload).when().post("/pet");
		System.out.println("response: " + response.asString());
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 201);
		Object petId = response.jsonPath().get("id");
		System.out.println("petId: " + petId);

		Response getResponse = given().log().all().header("accept", "application/json")
				.pathParam("petId", petId.toString()).when().get("/pet/{petId}");
		System.out.println("getResponse " + getResponse.asPrettyString());
		Object photoUrl = getResponse.jsonPath().get("photoUrls");
		System.out.println("photoUrl is: " + photoUrl);

//		Response putResp = given().log().all().header("accept", "application/json")
//				.header("Content-Type", "application/json").body("{\r\n" + "  \"name\": \"Cat\",\r\n" + "  \"photoUrls\": [\r\n"
//						+ "    \"https://petstore.swagger.io/#/pet/name/goldie\"\r\n" + "  ]\r\n" + "}").when()
//				.put("/pet")
//		System.out.println(response);

//		given().log().all().header("accept", "application/json").header("Content-Type", "application/json")
//				.pathParam("petId", "9223372036854248148").formParam("name", "Goldie").when().post("/pet/{petId}")
//				.then().log().all();
	}

	JSONObject jsonObj = new JSONObject();

}
