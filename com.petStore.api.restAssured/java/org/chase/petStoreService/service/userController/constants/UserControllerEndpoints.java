package org.chase.petStoreService.service.userController.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserControllerEndpoints {
	CREATE_USER_WITH_ARRAY("/user/createWithArray");

	@Getter
	private String value;
}
