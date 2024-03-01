package org.chase.petStoreService.service.petController;

import java.util.Map;

import io.restassured.response.Response;

public interface PetController {

	Response addPet(String payload);

	Response updatePet(String petId, Map<String, Object> formParam);

	Response getPetByStatus(String status);

	Response getPetByPetId(String petId);

}
