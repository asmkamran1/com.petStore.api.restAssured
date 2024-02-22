package org.chase.petStoreService.test.petStoreServiceTest.dataProviders;

import org.testng.annotations.DataProvider;

public class PetStoreServiceDataProvider {

	@DataProvider(name = "wrongMethodForGet")
	public String[] wrongMethodForGet() {
		return new String[] { "post", "put", "delete", "patch" };
	}

	@DataProvider(name = "petStatus")
	public String[] petStatus() {
		return new String[] { "available", "sold", "pending" };
	}

	@DataProvider(name = "validPetId")
	public String[] validPetId() {
		return new String[] { "1", "3", "4", "10" };
	}

	@DataProvider(name = "invalidPetId")
	public String[] invalidPetId() {
		return new String[] { "-1", "92233720368547758070" };
	}

}
