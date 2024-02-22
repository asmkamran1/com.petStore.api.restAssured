package org.chase.petStoreService.test;

import org.chase.petStoreService.configuration.ServiceConfiguration;
import org.chase.petStoreService.service.bookStoreService.BookStoreService;
import org.chase.petStoreService.service.petStoreService.PetStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = { ServiceConfiguration.class })
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

	@Autowired
	protected Environment environment;

	// services
	@Autowired
	protected PetStoreService petStoreService;

	@Autowired
	protected BookStoreService bookStoreService;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
