package com.enzulode.auth.exception.validation;

/**
 * VerifyTokenDTO validation exception.
 */
public class VerifyTokenDTONotValidException extends ModelValidationException
{
	/**
	 * VerifyTokenDTO validation exception constructor.
	 *
	 * @param message exception message
	 */
	public VerifyTokenDTONotValidException(String message)
	{
		super(message);
	}

	/**
	 * VerifyTokenDTO validation exception constructor with cause.
	 *
	 * @param message exception message
	 * @param cause   exception cause
	 */
	public VerifyTokenDTONotValidException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
