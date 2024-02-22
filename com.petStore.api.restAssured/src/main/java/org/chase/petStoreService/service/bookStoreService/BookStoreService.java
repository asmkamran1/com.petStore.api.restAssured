package org.chase.petStoreService.service.bookStoreService;

import org.chase.petStoreService.service.booksController.BooksControllerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("org.chase.petStoreService.service.bookStoreService")
public class BookStoreService {

	@Bean
	BooksControllerImpl booksController() {
		return new BooksControllerImpl();
	}
}
