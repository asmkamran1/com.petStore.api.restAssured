package org.chase.petStoreService.service.userController;

import org.chase.petStoreService.environment.EnvironmentConfigurations;
import org.chase.petStoreService.service.userController.constants.UserControllerEndpoints;
import org.chase.utilities.apiUtilities.APIUtils;
import org.springframework.stereotype.Controller;

import io.restassured.response.Response;

@Controller
public class UserControllerImpl extends APIUtils implements UserController {

	@Override
	public Response createUserWithArray(String payload) {
		return requestSpecification(EnvironmentConfigurations.petStoreBaseURI).body(payload).when()
				.post(UserControllerEndpoints.CREATE_USER_WITH_ARRAY.getValue());
	}

}
