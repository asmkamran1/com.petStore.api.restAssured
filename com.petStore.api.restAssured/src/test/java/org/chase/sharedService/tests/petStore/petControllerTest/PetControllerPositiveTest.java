package org.chase.sharedService.tests.petStore.petControllerTest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.chase.petStoreService.environment.EnvironmentConfigurations;
import org.chase.utilities.apiUtilities.APIUtils;
import org.chase.utilities.commmonUtilities.CommonUtilities;
import org.chase.utilities.commmonUtilities.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PetControllerPositiveTest extends APIUtils {

	@Test
	public void addPet_requiredFieldsOnly_expect_200() throws IOException {
		String baseURI = "https://petstore.swagger.io/v2";
		String payload = CommonUtilities.getJSON(
				"src\\test\\resources\\json\\petStoreService\\petStoreController\\addPet_requiredOnly_payload.json")
				.replace(":name", "Browny");
		System.out.println(payload);

//		Response response = .when().post("/pet");
//		System.out.println("response: " + response.asPrettyString());
//		int statusCode = response.getStatusCode();
//		Assert.assertEquals(statusCode, 201);
//		System.out.println("int max value:" + Integer.MAX_VALUE);
//		Object petId = response.jsonPath().get("id");
//		String name = response.jsonPath().get("name");
//		System.out.println("petId: " + petId);

		Response getResponse = given().log().all().baseUri(baseURI).header("accept", "application/json")
				.pathParam("petId", "123").when().get("/pet/{petId}");
		// given().log().all().baseUri(baseURI).header("accept", "application/json")
		// .pathParam("petId", petId)
		// requestSpecification
		System.out.println("getResponse: " + getResponse);
	}

	@Test(groups = { "regression", "petController" })
	public void addPet_status_expect_200() throws IOException {
		String baseURI = "https://petstore.swagger.io/v2";
		String payload = CommonUtilities
				.getJSON("src/test/resources/json/petStoreService/petStoreController/addPet_status_payload.json")
				.replace(":name", "Browny").replace(":status", "sold");
		String payload2 = CommonUtilities.getJSON(
				"src\\test\\resources\\json\\petStoreService\\petStoreController\\addPet_requiredOnly_payload.json")
				.replace(":name", "Browny");
//		Assert.assertEquals(false, null);
		System.out.println(payload);
//		
//		given().baseUri(baseURI).log().all().header("accept", "application/json")
//		.header("Content-Type", "application/json").body(null)

//		given().baseUri(baseURI).log().all().accept("application/json").body(payload).contentType("application/json")
//				.header("key", "value").when().post("/pet").then().log().all();

		given().baseUri(baseURI).log().all().header("accept", "application/json")
				.header("Content-Type", "application/json").body(payload).when().post("/pet").then().log().all();

		JSONObject jsonObj = new JSONObject(payload2);
		System.out.println("jsonObj: " + jsonObj);
		jsonObj.put("status", "available");
		System.out.println("jsonObj: " + jsonObj);
		jsonObj.remove("name");
		jsonObj.remove("photoUrls");
		jsonObj.put("name", "Jabed");
		jsonObj.put("status", "sold");
		jsonObj.get("name");
		System.out.println("jsonObj: " + jsonObj);
		jsonObj.put("status", "available");
//		jsonObj.clear();
		System.out.println("jsonObj: " + jsonObj);

		Response getPetByStatus = given().log().all().baseUri(baseURI).accept("application/json")
				.queryParam("status", "available").when().get("/pet/findByStatus");
		// RequestSpecificaion
//		String name = getPetByStatus.jsonPath().getString("[0].tags[0].name");

		JSONArray jsonArr = new JSONArray(getPetByStatus.asPrettyString());
		System.out.println("response: " + jsonArr.toString());
		JSONObject nameObj = jsonArr.getJSONObject(16);
		nameObj.toMap();
		System.out.println("nameObj: " + nameObj);
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject obj = jsonArr.getJSONObject(i);
			Object nameElement = obj.get("name");
			System.out.println(nameElement.toString());
		}
	}

	public void updatePet_name_expect_200() {
//		String name = "Pet";
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("name", "Goldie");

//		// only name
//		formParam.put("name", "Kitty");
//		
//		
//		// only status
//		formParam.clear();
//		formParam.put("status", "Sold");
	}

	public void updatePet_status_expect_200() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("status", "sold");
	}

	public void updatePet_all_expect_200() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("status", "sold");
		queryParams.put("name", "Goldie");
	}

	public void temp() {

	}

	@Test
	public void temp2() {
		String petStoreBaseURI = EnvironmentConfigurations.petStoreBaseURI;
		System.out.println(petStoreBaseURI);
		String PROJECT_PATH = System.getProperty("user.dir");
		System.out.println(PROJECT_PATH);
		// C:\Users\akamran\eclipse-workspace\org.chase.api.automation\src\test\resources\json\petStoreService\petStoreController\addPet_status_payload.json

		String payload = Utilities.getJSON("petStoreService\\petStoreController\\addPet_status_payload.json");
		System.out.println(payload);
	}
	
	@Test
	public void temp_kamran() {
		System.out.println("Github demo 1");
	}

}
