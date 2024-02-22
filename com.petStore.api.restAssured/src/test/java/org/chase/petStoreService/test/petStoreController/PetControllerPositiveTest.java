package org.chase.petStoreService.test.petStoreController;

import org.chase.petStoreService.test.BaseTest;
import org.chase.petStoreService.test.petStoreServiceTest.dataProviders.PetStoreServiceDataProvider;
import org.chase.utilities.commmonUtilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PetControllerPositiveTest extends BaseTest {

	@Test(groups = { "regression", "smoke", "petController", "petControllerPositive" })
	public void addPet_requiredFieldsOnly_expect_200() {
		// Scenario: Invoke the API with mandatory fields only.
		String petName = "Goldie";
		String payload = Utilities.getJSON("petStoreService/petStoreController/addPet_requiredOnly_payload.json")
				.replace(":name", petName);
		System.out.println(payload);

		Response response = petStoreService.petController().addPet(payload);
		Assert.assertEquals(response.statusCode(), 200);
//		Response response = petStoreService.petController().addPet(payload);

	}

	@Test(groups = { "regression",
			"sanity" }, dataProvider = "petStatus", dataProviderClass = PetStoreServiceDataProvider.class)
	public void getPetByStatus_allStaus_expect_200(String status) {
		System.out.println("The current status from dataProvider is " + status);
		System.out.println("Scenario: Invoke the API with " + status + " status");
		Response resp = petStoreService.petController().getPetByStatus(status);
//		System.out.println(resp.asPrettyString());
	}

	@Test(groups = { "regression",
			"sanity" }, dataProvider = "validPetId", dataProviderClass = PetStoreServiceDataProvider.class)
	public void getPetByPetId(String petId) {
		System.out.println("The pet id is " + petId);
		Response resp = petStoreService.petController().getPetByPetId(petId);
	}

}
