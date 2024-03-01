package org.chase.petStoreService.service.userController;

import io.restassured.response.Response;

public interface UserController {
	Response createUserWithArray(String payload);
}
