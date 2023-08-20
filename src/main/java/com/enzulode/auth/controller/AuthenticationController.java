package com.enzulode.auth.controller;

import com.enzulode.auth.exception.authentication.AuthenticationException;
import com.enzulode.auth.exception.validation.ModelValidationException;
import com.enzulode.auth.model.data.User;
import com.enzulode.auth.model.dto.CreateUserDTO;
import com.enzulode.auth.model.dto.GenerateTokenDTO;
import com.enzulode.auth.model.dto.VerifyTokenDTO;
import com.enzulode.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Authentication rest controller.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController
{

	/**
	 * Application authentication service instance.
	 */
	private final AuthenticationService authenticationService;

	/**
	 * This method is responsible to handle user creation requests.
	 *
	 * @param userDTO user create dto
	 * @return user instance or error message otherwise
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createNewUser(@RequestBody CreateUserDTO userDTO)
	{
		try
		{
			Optional<User> user = authenticationService.createNewUser(userDTO);
			return (user.isEmpty())
					? ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists")
					: ResponseEntity.ok(user.get());
		}
		catch (ModelValidationException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong: " + e.getMessage());
		}
	}

	/**
	 * This method handles token generation request for a specific user.
	 *
	 * @param generateTokenDTO generate token DTO instance
	 * @return new token for an exact user or an error message otherwise
	 */
	@PostMapping("/token")
	public ResponseEntity<?> assumeToken(@RequestBody GenerateTokenDTO generateTokenDTO)
	{
		try
		{
			return ResponseEntity.ok(authenticationService.generateToken(generateTokenDTO));
		}
		catch (ModelValidationException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong: " + e.getMessage());
		}
		catch (AuthenticationException e)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials: " + e.getMessage());
		}
	}

	/**
	 * This method receives token verification requests and handles them.
	 *
	 * @param verifyTokenDTO token verification DTO
	 * @return success message with HTTP status 200 (OK), or failure message with HTTP status 400 (BAD REQUEST)
	 */
	@PostMapping("/verify")
	public ResponseEntity<?> verifyToken(@RequestBody VerifyTokenDTO verifyTokenDTO)
	{
		try
		{
			authenticationService.verifyToken(verifyTokenDTO);
			return ResponseEntity.ok("Token is verified");
		}
		catch (ModelValidationException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong: " + e.getMessage());
		}
	}

}
