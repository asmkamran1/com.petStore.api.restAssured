package org.chase.petStoreService.environment;

import org.chase.utilities.commmonUtilities.EnvironmentUtilities;

public class EnvironmentConfigurations {
	private static String getBaseURI(String key) {
		String testEnv = EnvironmentUtilities.urls.get(key).getAsString();
		return testEnv;
	}

	public static final String petStoreBaseURI = "https://petstore.swagger.io/v2";
	public static final String bookStoreBaseURI = "https://fakerestapi.azurewebsites.net";
//	public static final String petStoreBaseURI = getBaseURI("petStore");

}
