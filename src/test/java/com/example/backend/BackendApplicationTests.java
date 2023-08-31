package com.example.backend;

import com.example.backend.controller.CabinController;
import com.example.backend.repo.CabinRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
@ExtendWith(MockitoExtension.class)
public abstract class BackendApplicationTests {
	@Container
	private static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.0.30")
			.withDatabaseName("testcontainer")
			.withUsername("test")
			.withPassword("test");

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@DynamicPropertySource
	private static void setupProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mySQLContainer::getUsername);
		registry.add("spring.datasource.password", mySQLContainer::getPassword);
	}

	@Test
	void testMySQLContainerIsRunning() {
		Assertions.assertTrue(mySQLContainer.isRunning());
	}

}