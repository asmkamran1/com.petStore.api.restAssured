package org.chase.petStoreService.service.booksController;

import org.chase.petStoreService.environment.EnvironmentConfigurations;
import org.chase.petStoreService.service.booksController.constants.BooksControllerEndpoints;
import org.chase.utilities.apiUtilities.APIUtils;
import org.springframework.stereotype.Controller;

import io.restassured.response.Response;

@Controller
public class BooksControllerImpl extends APIUtils implements BooksController {

	@Override
	public Response getBooks() {
		return requestSpecAcceptText(EnvironmentConfigurations.bookStoreBaseURI)
				.get(BooksControllerEndpoints.GET_BOOKS.getValue());
	}

}
