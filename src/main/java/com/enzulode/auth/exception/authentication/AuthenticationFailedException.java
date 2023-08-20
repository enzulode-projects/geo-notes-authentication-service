package com.enzulode.auth.exception.authentication;

/**
 * This exception means that authentication not succeed.
 */
public class AuthenticationFailedException extends AuthenticationException
{
	/**
	 * Authentication exception constructor.
	 *
	 * @param message exception message
	 */
	public AuthenticationFailedException(String message)
	{
		super(message);
	}

	/**
	 * Authentication exception constructor with cause.
	 *
	 * @param message exception message
	 * @param cause   exception cause
	 */
	public AuthenticationFailedException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
