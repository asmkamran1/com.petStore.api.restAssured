package org.chase.petStoreService.service.petStoreService;

import org.chase.petStoreService.service.petController.PetControllerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("org.chase.petStoreService.service.petStoreService")
public class PetStoreService {

	@Bean
	public PetControllerImpl petController() {
		return new PetControllerImpl();
	}

//	@Bean
//	public StoreControllerImpl storeController() {
//		return new StoreControllerImpl();
//	}
}
