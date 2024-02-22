package org.chase.petStoreService.configuration;

import org.chase.petStoreService.service.bookStoreService.BookStoreService;
import org.chase.petStoreService.service.petStoreService.PetStoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "org.chase.petStoreService" })
public class ServiceConfiguration {
	@Bean
	public PetStoreService petStoreService() {
		return new PetStoreService();
	}

	@Bean
	public BookStoreService bookStoreService() {
		return new BookStoreService();
	}

}
