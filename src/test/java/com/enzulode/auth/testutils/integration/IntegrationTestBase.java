package com.enzulode.auth.testutils.integration;

import com.enzulode.auth.testutils.integration.initializer.PostgreSQL;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * Base class for all integration tests.
 */
@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@ContextConfiguration(initializers = {
		PostgreSQL.Initializer.class
})
public abstract class IntegrationTestBase
{

	/**
	 * Integration tests environment setup.
	 */
	@BeforeAll
	public static void init()
	{
		PostgreSQL.CONTAINER.start();
	}

}
