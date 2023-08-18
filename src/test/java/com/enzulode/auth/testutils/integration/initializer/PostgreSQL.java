package com.enzulode.auth.testutils.integration.initializer;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * PostgreSQL integration testing utility class.
 */
public class PostgreSQL
{

	/**
	 * PostgreSQL container instance.
	 */
	public static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:15.2")
			.withDatabaseName("test")
			.withUsername("test")
			.withPassword("test");

	/**
	 * PostgreSQL integration test spring application context initializer.
	 */
	public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>
	{

		/**
		 * Initialize the given application context.
		 *
		 * @param applicationContext the application to configure
		 */
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext)
		{
			TestPropertyValues.of(
					"spring.datasource.url=" + CONTAINER.getJdbcUrl(),
					"spring.datasource.username=" + CONTAINER.getUsername(),
					"spring.datasource.password=" + CONTAINER.getPassword(),

					"spring.flyway.url=" + CONTAINER.getJdbcUrl(),
					"spring.flyway.username=" + CONTAINER.getUsername(),
					"spring.flyway.password=" + CONTAINER.getPassword()
			).applyTo(applicationContext.getEnvironment());
		}
	}
}
