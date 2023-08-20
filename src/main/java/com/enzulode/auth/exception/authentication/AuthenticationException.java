package com.enzulode.auth.exception.authentication;

/**
 * Authentication-related exception abstraction.
 */
public abstract class AuthenticationException extends Exception
{

	/**
	 * Authentication exception constructor.
	 *
	 * @param message exception message
	 */
	public AuthenticationException(String message)
	{
		super(message);
	}

	/**
	 * Authentication exception constructor with cause.
	 *
	 * @param message exception message
	 * @param cause exception cause
	 */
	public AuthenticationException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
