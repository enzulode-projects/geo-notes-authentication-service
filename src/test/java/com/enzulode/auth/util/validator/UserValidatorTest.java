package com.enzulode.auth.util.validator;

import com.enzulode.auth.model.data.User;
import com.enzulode.auth.util.ModelValidator;
import com.enzulode.auth.util.UserValidatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link UserValidatorImpl} related tests.
 */
public class UserValidatorTest
{
	/**
	 * User validator instance.
	 */
	public static final ModelValidator<User> userValidator = new UserValidatorImpl();

	/**
	 * This method checks user validation by id.
	 */
	@Test
	@DisplayName("Test validation: invalid user id")
	public void test_validate_userWithNoId_expected_false()
	{
		User user = new User(null, "nick", "pass", Date.from(Instant.now()));
		assertFalse(userValidator.validate(user), "Expected false, but true occurred");
	}

	/**
	 * This method checks user validation by nickname (with null nickname).
	 */
	@Test
	@DisplayName("Test validation: invalid user nickname(null)")
	public void test_validate_userWithNoNickname_expected_false()
	{
		User user = new User(1L, null, "pass", Date.from(Instant.now()));
		assertFalse(userValidator.validate(user), "Expected false, but true occurred");
	}

	/**
	 * This method checks user validation by nickname (with blank nickname).
	 */
	@Test
	@DisplayName("Test validation: invalid user nickname(blank)")
	public void test_validate_userWithBlankNickname_expected_false()
	{
		User user = new User(1L, "", "pass", Date.from(Instant.now()));
		assertFalse(userValidator.validate(user), "Expected false, but true occurred");
	}

	/**
	 * This method checks user validation by password (with null password).
	 */
	@Test
	@DisplayName("Test validation: invalid user password(null)")
	public void test_validate_userWithNoPassword_expected_false()
	{
		User user = new User(1L, "nick", null, Date.from(Instant.now()));
		assertFalse(userValidator.validate(user), "Expected false, but true occurred");
	}

	/**
	 * This method checks user validation by password (with blank password).
	 */
	@Test
	@DisplayName("Test validation: invalid user password(blank)")
	public void test_validate_userWithBlankPassword_expected_false()
	{
		User user = new User(1L, "nick", "", Date.from(Instant.now()));
		assertFalse(userValidator.validate(user), "Expected false, but true occurred");
	}

	/**
	 * This method checks user validation by creation date (with null creation date).
	 */
	@Test
	@DisplayName("Test validation: invalid user creation date(null)")
	public void test_validate_userWithNoCreationDate_expected_false()
	{
		User user = new User(1L, "nick", "pass", null);
		assertFalse(userValidator.validate(user), "Expected false, but true occurred");
	}

	/**
	 * This method checks validation for valid user.
	 */
	@Test
	@DisplayName("Test validation: valid user")
	public void test_validate_validUser_expected_true()
	{
		User user = new User(1L, "nick", "pass", Date.from(Instant.now()));
		assertTrue(userValidator.validate(user), "Expected true, but false occurred");
	}
}
