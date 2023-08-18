package com.enzulode.auth.dao;

import com.enzulode.auth.model.data.User;
import com.enzulode.auth.testutils.Utils;
import com.enzulode.auth.testutils.integration.IntegrationTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserDAOImpl integration tests.
 */
@Transactional
public class UserDAOImplIntegrationTest extends IntegrationTestBase
{

	/**
	 * UserDAOImpl instance.
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * This property contains random generator for testing purposes.
	 */
	private static final RandomGenerator randomGenerator = new Random();

	/**
	 * This method tests user insertion in the database.
	 */
	@Test
	@DisplayName("Test createUser(): expected result greater than zero")
	public void test_createUser_expected_result_greater_than_zero()
	{
		User user = new User(
				10L,
				"enzulodeasd",
				"paasss",
				Date.from(Instant.now())
		);
		long result = userDAO.createUser(user);
		assertTrue(result > 0);
	}

	/**
	 * This method tests user fetching by id from the database.
	 */
	@Test
	@DisplayName("Test findById(): expected not empty result")
	public void test_findById_expected_not_empty()
	{
		User user = new User(
				17L,
				"enz",
				"pass",
				Date.from(Instant.now())
		);
		long id = userDAO.createUser(user);
		Optional<User> optionalUser = userDAO.findById(id);

		assertTrue(optionalUser.isPresent(), "Testing user was not created, failure");
		assertEquals(optionalUser.get(), user, "Users should be equal");
	}

	/**
	 * This method tests user fetching via pages.
	 */
	@Test
	@DisplayName("Test findUsersForPage(): expected users fetched correctly")
	public void test_findUsersForPage_expected_users_fetched_correctly()
	{
//		Create users in the database
		List<User> randomUsers = Utils.generateRandomUsers();
		randomUsers.forEach(userDAO::createUser);

//		Fetch users from the database
		int pageSize = 5;
		List<User> fetchedUsers = new ArrayList<>();
		for (int i = 0; i < (userDAO.count() / pageSize); i++)
		{
			Page<User> userPage = userDAO.findUsersForPage(PageRequest.of(i, pageSize));
			fetchedUsers.addAll(userPage.get().toList());
		}

		assertEquals(randomUsers.size(), fetchedUsers.size(), "Sizes should be equal");
		assertEquals(randomUsers, fetchedUsers, "Lists should be equal");
	}

	/**
	 * This method tests user update procedure.
	 */
	@Test
	@DisplayName("Test updateUser(): expected update succeed")
	public void test_updateUser_expected_update_succeed()
	{
		User defaultUser = new User(
				1L,
				"dev-1",
				"pass",
				Date.from(Instant.now())
		);
		userDAO.createUser(defaultUser);
		User after = new User(
				defaultUser.getId(),
				"dev-2",
				defaultUser.getPassword(),
				defaultUser.getCreationDate()
		);
		assertTrue(userDAO.updateUser(after), "User update should succeed, but something went wrong");
	}

	/**
	 * This method tests user deletion procedure.
	 */
	@Test
	@DisplayName("Test deleteUser(): expected delete user succeed")
	public void test_deleteUser_expected_user_deletion_succeed()
	{
		User user = new User(10L, "dev", "pass", Date.from(Instant.now()));
		userDAO.createUser(user);
		assertTrue(userDAO.deleteUser(user.getId()));
	}
}
