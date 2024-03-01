package org.chase.petStoreService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetStorecServiceSpringBootApp {
	public static final Logger log = LogManager.getLogger(PetStorecServiceSpringBootApp.class);

	public static void main(String[] args) {
		SpringApplication.run(PetStorecServiceSpringBootApp.class, args);
	}
}
