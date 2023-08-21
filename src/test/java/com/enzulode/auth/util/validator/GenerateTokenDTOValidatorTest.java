package com.enzulode.auth.util.validator;

import com.enzulode.auth.model.dto.GenerateTokenDTO;
import com.enzulode.auth.util.GenerateTokenDTOValidatorImpl;
import com.enzulode.auth.util.ModelValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * GenerateTokenDTO-related tests.
 */
public class GenerateTokenDTOValidatorTest
{

	/**
	 * Create user DTO validator instance.
	 */
	private static final ModelValidator<GenerateTokenDTO> generateTokenDTOValidator = new GenerateTokenDTOValidatorImpl();

	/**
	 * This method tests that validator correctly validates dto with no nickname in it.
	 */
	@Test
	@DisplayName("Test generateTokenDTO validation: no nickname")
	public void test_validate_noNickname_expected_false()
	{
		GenerateTokenDTO dto = new GenerateTokenDTO(null, "pass");
		assertFalse(
				generateTokenDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with blank nickname.
	 */
	@Test
	@DisplayName("Test generateTokenDTO validation: blank nickname")
	public void test_validate_blankNickname_expected_false()
	{
		GenerateTokenDTO dto = new GenerateTokenDTO("", "pass");
		assertFalse(
				generateTokenDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with correct nickname.
	 */
	@Test
	@DisplayName("Test generateTokenDTO validation: correct nickname")
	public void test_validate_correctNickname_expected_true()
	{
		GenerateTokenDTO dto = new GenerateTokenDTO("nick", "pass");
		assertTrue(
				generateTokenDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with no password in it.
	 */
	@Test
	@DisplayName("Test generateTokenDTO validation: no password")
	public void test_validate_noPassword_expected_false()
	{
		GenerateTokenDTO dto = new GenerateTokenDTO("nick", null);
		assertFalse(
				generateTokenDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with blank password.
	 */
	@Test
	@DisplayName("Test generateTokenDTO validation: blank password")
	public void test_validate_blankPassword_expected_false()
	{
		GenerateTokenDTO dto = new GenerateTokenDTO("nick", "");
		assertFalse(
				generateTokenDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}

	/**
	 * This method tests that validator correctly validates dto with correct nickname.
	 */
	@Test
	@DisplayName("Test generateTokenDTO validation: correct password")
	public void test_validate_correctPassword_expected_true()
	{
		GenerateTokenDTO dto = new GenerateTokenDTO("nick", "pass");
		assertTrue(
				generateTokenDTOValidator.validate(dto),
				"Expected to be false, but true occurred"
		);
	}
}
