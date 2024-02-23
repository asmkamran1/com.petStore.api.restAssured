package org.chase.petStoreService.test.petStoreController;

import org.chase.petStoreService.test.BaseTest;
import org.chase.petStoreService.test.petStoreServiceTest.dataProviders.PetStoreServiceDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PetControllerNegativeTest extends BaseTest {

	@Test
	public void addPet_expect_400() {
		// Scenario: Invoke the API without any payload
		/*
		 * When to expect 400 response: no payload missing required field(s) of a
		 * payload missing required parameters (Path parameter/Query parameter invalid
		 * data type - JSON key is expecting an integer but we are sending a string
		 */
		String payload = "";
		Response response = petStoreService.petController().addPet(payload);
		Assert.assertEquals(response.statusCode(), 400);

		// GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS
	}

	@Test(dataProvider = "invalidPetId", dataProviderClass = PetStoreServiceDataProvider.class)
	public void getPetById_expect_404(String id) {

		System.out.println("Test data " + id);
		Response resp = petStoreService.petController().getPetByPetId(id);
		System.out.println(resp.asString());
		Assert.assertEquals(resp.statusCode(), 404);
	}

	@Test(dataProvider = "invalidPetId", dataProviderClass = PetStoreServiceDataProvider.class)
	public void getPetById_expect_401(String id) {

		System.out.println("Test data " + id);
		Response resp = petStoreService.petController().getPetByPetId(id);
		System.out.println(resp.asString());
		Assert.assertEquals(resp.statusCode(), 404);
	}

	@Test
	public void demo_negTest() {
		"Demo Negtive test"
	}
}
