package org.chase.petStoreService.service.petController;

import java.util.Map;

import org.chase.petStoreService.environment.EnvironmentConfigurations;
import org.chase.petStoreService.service.petController.constants.PetControllerEndpoints;
import org.chase.utilities.apiUtilities.APIUtils;
import org.springframework.stereotype.Controller;

import io.restassured.response.Response;

@Controller
public class PetControllerImpl extends APIUtils implements PetController {

	@Override
	public Response addPet(String payload) {

		return requestSpecification(EnvironmentConfigurations.petStoreBaseURI).body(payload)
				.post(PetControllerEndpoints.ADD_PET.getValue());

		// given().log().all().baseUri(baseUri).relaxedHTTPSValidation().accept(MediaType.APPLICATION_JSON_VALUE).body(payload)
		// .when().post("/pet");
	}

	@Override
	public Response updatePet(String petId, Map<String, Object> formParam) {

		return requestSpecificationFormParam(EnvironmentConfigurations.petStoreBaseURI).pathParam("petId", petId)
				.formParams(formParam).when().post(PetControllerEndpoints.UPDATE_PET.getValue());
	}

//	@Override
//	public Response updatePet(String petId, String name, String status) {
//
//		return requestSpecificationFormParam("https://petstore.swagger.io/v2").pathParam("petId", petId)
//				.formParam("name", name).formParam("status", status).when()
//				.post(PetControllerEndpoints.UPDATE_PET.getValue());
//	}
//
//	@Override
//	public Response updatePet(String petId, String name) {
//
//		return requestSpecificationFormParam("https://petstore.swagger.io/v2").pathParam("petId", petId)
//				.formParam("name", name).when().post(PetControllerEndpoints.UPDATE_PET.getValue());
//	}
//
//	@Override
//	public Response updatePet(String petId, String status) {
//
//		return requestSpecificationFormParam("https://petstore.swagger.io/v2").pathParam("petId", petId)
//				.formParam("status", status).when().post(PetControllerEndpoints.UPDATE_PET.getValue());
//	}

	@Override
	public Response getPetByStatus(String status) {
		return requestSpecAcceptJSON(EnvironmentConfigurations.petStoreBaseURI).queryParam("status", status)
				.get(PetControllerEndpoints.GET_PET_BY_STATUS.getValue());
	}

	@Override
	public Response getPetByPetId(String petId) {
		return requestSpecAcceptJSON(EnvironmentConfigurations.petStoreBaseURI).pathParam("petId", petId)
				.get(PetControllerEndpoints.GET_PET_BY_PET_ID.getValue());
	}

}
