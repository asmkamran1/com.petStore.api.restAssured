package org.chase.petStoreService.service.booksController.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BooksControllerEndpoints {

	GET_BOOKS("/api/v1/Books");

	@Getter
	private String value;
}
