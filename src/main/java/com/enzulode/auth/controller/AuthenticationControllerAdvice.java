package com.enzulode.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

/**
 * This class contains exception handling methods related to authentication controller.
 */
@ControllerAdvice
public class AuthenticationControllerAdvice
{

	/**
	 * This method handles the situation when user tries to access resources with an expired token.
	 *
	 * @return 403 HTTP status (FORBIDDEN) and error message as a response entity
	 */
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<?> handle()
	{
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication token expired");
	}
}
