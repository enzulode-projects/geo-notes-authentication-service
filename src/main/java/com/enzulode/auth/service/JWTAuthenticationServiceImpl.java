package com.enzulode.auth.service;

import com.enzulode.auth.dao.UserDAO;
import com.enzulode.auth.exception.authentication.AuthenticationFailedException;
import com.enzulode.auth.exception.validation.CreateUserDTONotValidException;
import com.enzulode.auth.exception.validation.GenerateTokenDTONotValidException;
import com.enzulode.auth.exception.validation.VerifyTokenDTONotValidException;
import com.enzulode.auth.model.data.User;
import com.enzulode.auth.model.dto.CreateUserDTO;
import com.enzulode.auth.model.dto.GenerateTokenDTO;
import com.enzulode.auth.model.dto.VerifyTokenDTO;
import com.enzulode.auth.util.ModelValidator;
import com.enzulode.auth.util.TokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

/**
 * JWT-based authentication service implementation.
 */
@Service
@RequiredArgsConstructor
public class JWTAuthenticationServiceImpl implements AuthenticationService
{

	/**
	 * CreateUserDTO validator instance.
	 */
	private final ModelValidator<CreateUserDTO> createUserDTOValidator;

	/**
	 * GenerateTokenDTO validator instance.
	 */
	private final ModelValidator<GenerateTokenDTO> generateTokenDTOValidator;

	/**
	 * VerifyTokenDTO validator instance.
	 */
	private final ModelValidator<VerifyTokenDTO> verifyTokenDTOValidator;

	/**
	 * UserDAO instance.
	 */
	private final UserDAO userDAO;

	/**
	 * Application password encoder instance.
	 */
	private final PasswordEncoder passwordEncoder;

	/**
	 * TokenHelper instance.
	 */
	private final TokenHelper tokenHelper;

	/**
	 * Application AuthenticationManager instance.
	 */
	private final AuthenticationManager authenticationManager;

	/**
	 * This method creates new user.
	 *
	 * @param userDTO user data from request
	 * @throws CreateUserDTONotValidException if incoming {@link CreateUserDTO} is not valid
	 */
	@Override
	public Optional<User> createNewUser(CreateUserDTO userDTO) throws CreateUserDTONotValidException
	{
		if (!createUserDTOValidator.validate(userDTO))
			throw new CreateUserDTONotValidException("Invalid params for user creation");

		User user = new User(
				null,
				userDTO.nickname(),
				passwordEncoder.encode(userDTO.password()),
				Date.from(Instant.now())
		);
		long result = userDAO.createUser(user);

		if (result < 1)
			return Optional.empty();

		return Optional.of(user);
	}

	/**
	 * This method generates new token.
	 *
	 * @param generateTokenDTO token generating DTO
	 * @return authentication token
	 * @throws GenerateTokenDTONotValidException if incoming {@link GenerateTokenDTO} is not valid
	 * @throws AuthenticationFailedException if incoming {@link GenerateTokenDTO} not contains appropriate
	 * user to be authenticated
	 */
	@Override
	public String generateToken(GenerateTokenDTO generateTokenDTO)
			throws GenerateTokenDTONotValidException, AuthenticationFailedException
	{
		if (!generateTokenDTOValidator.validate(generateTokenDTO))
			throw new GenerateTokenDTONotValidException("Invalid params for token generating");

		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						generateTokenDTO.nickname(),
						generateTokenDTO.password()
				)
		);

		if (!auth.isAuthenticated())
			throw new AuthenticationFailedException("User authentication failed");

		return tokenHelper.generateToken(generateTokenDTO.nickname());
	}

	/**
	 * This method verifies already existing token.
	 *
	 * @param verifyTokenDTO token verification DTO
	 * @throws VerifyTokenDTONotValidException if incoming {@link VerifyTokenDTO} is not valid
	 */
	@Override
	public void verifyToken(VerifyTokenDTO verifyTokenDTO) throws VerifyTokenDTONotValidException
	{
		if (!verifyTokenDTOValidator.validate(verifyTokenDTO))
			throw new VerifyTokenDTONotValidException("Invalid params for token verification");

		tokenHelper.verifyToken(verifyTokenDTO.token());
	}
}
