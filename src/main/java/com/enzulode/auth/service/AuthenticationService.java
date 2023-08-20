package com.enzulode.auth.service;

import com.enzulode.auth.exception.authentication.AuthenticationException;
import com.enzulode.auth.exception.validation.ModelValidationException;
import com.enzulode.auth.model.data.User;
import com.enzulode.auth.model.dto.CreateUserDTO;
import com.enzulode.auth.model.dto.GenerateTokenDTO;
import com.enzulode.auth.model.dto.VerifyTokenDTO;

import java.util.Optional;

/**
 * Authentication service contract.
 */
public interface AuthenticationService
{

	/**
	 * This method creates new user.
	 *
	 * @param userDTO user data from request
	 * @return optional created user
	 * @throws ModelValidationException if incoming DTO is invalid
	 */
	Optional<User> createNewUser(CreateUserDTO userDTO) throws ModelValidationException;

	/**
	 * This method generates new token.
	 *
	 * @param generateTokenDTO token generating DTO
	 * @return authentication token
	 * @throws ModelValidationException if incoming DTO is invalid
	 */
	String generateToken(GenerateTokenDTO generateTokenDTO) throws ModelValidationException, AuthenticationException;

	/**
	 * This method verifies already existing token.
	 *
	 * @param verifyTokenDTO token verification DTO
	 * @throws ModelValidationException if incoming DTO is invalid
	 */
	void verifyToken(VerifyTokenDTO verifyTokenDTO) throws ModelValidationException;
}
