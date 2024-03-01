package org.chase.petStoreService.service.petController.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PetControllerEndpoints {

	ADD_PET("/pet"),

	UPDATE_PET("/pet"),

	GET_PET_BY_STATUS("/pet/findByStatus"),

	GET_PET_BY_PET_ID("/pet/{petId}");

	@Getter
	private String value;

}
