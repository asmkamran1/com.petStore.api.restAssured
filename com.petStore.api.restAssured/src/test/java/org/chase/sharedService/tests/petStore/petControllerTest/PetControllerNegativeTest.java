package org.chase.sharedService.tests.petStore.petControllerTest;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;

public class PetControllerNegativeTest {

	JSONObject jsonObj = new JSONObject();

	RequestSpecification req() {
		return null;
	}

	@Test
	public void temp() {
		String PROJECT_PATH = System.getProperty("user.dir");
		System.out.println(PROJECT_PATH);
	}

}
