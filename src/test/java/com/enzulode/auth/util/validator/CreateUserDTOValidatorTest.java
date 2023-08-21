package com.enzulode.auth.util.validator;

import com.enzulode.auth.model.dto.CreateUserDTO;
import com.enzulode.auth.util.CreateUserDTOValidatorImpl;
import com.enzulode.auth.util.ModelValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CreateUserDTO-related tests.
 */
public class CreateUserDTOValidatorTest
{

	/**
	 * Create user DTO validator instance.
	 */
	private static final ModelValidator<CreateUserDTO> createUserDTOValidator = new CreateUserDTOValidatorImpl();

	/**
	 * This method tests that validator correctly validates dto with no nickname in it.
	 */
	@Test
	@DisplayName("Test createUserDTO validation: no nickname")
	public void test_validate_noNickname_expected_false()
	{
		CreateUserDTO dto = new CreateUserDTO(null, "pass");
		assertFalse(
				createUserDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with blank nickname.
	 */
	@Test
	@DisplayName("Test createUserDTO validation: blank nickname")
	public void test_validate_blankNickname_expected_false()
	{
		CreateUserDTO dto = new CreateUserDTO("", "pass");
		assertFalse(
				createUserDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with correct nickname.
	 */
	@Test
	@DisplayName("Test createUserDTO validation: correct nickname")
	public void test_validate_correctNickname_expected_true()
	{
		CreateUserDTO dto = new CreateUserDTO("nick", "pass");
		assertTrue(
				createUserDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with no password in it.
	 */
	@Test
	@DisplayName("Test createUserDTO validation: no password")
	public void test_validate_noPassword_expected_false()
	{
		CreateUserDTO dto = new CreateUserDTO("nick", null);
		assertFalse(
				createUserDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with blank password.
	 */
	@Test
	@DisplayName("Test createUserDTO validation: blank password")
	public void test_validate_blankPassword_expected_false()
	{
		CreateUserDTO dto = new CreateUserDTO("nick", "");
		assertFalse(
				createUserDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with correct nickname.
	 */
	@Test
	@DisplayName("Test createUserDTO validation: correct password")
	public void test_validate_correctPassword_expected_true()
	{
		CreateUserDTO dto = new CreateUserDTO("nick", "pass");
		assertTrue(
				createUserDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}
}
