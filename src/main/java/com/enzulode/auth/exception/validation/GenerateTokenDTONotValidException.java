package com.enzulode.auth.exception.validation;

/**
 * GenerateTokenDTO validation exception.
 */
public class GenerateTokenDTONotValidException extends ModelValidationException
{
	/**
	 * GenerateTokenDTO validation exception constructor.
	 *
	 * @param message exception message
	 */
	public GenerateTokenDTONotValidException(String message)
	{
		super(message);
	}

	/**
	 * GenerateTokenDTO validation exception constructor with cause.
	 *
	 * @param message exception message
	 * @param cause   exception cause
	 */
	public GenerateTokenDTONotValidException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
