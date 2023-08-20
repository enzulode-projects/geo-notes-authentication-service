package com.enzulode.auth.exception.validation;

/**
 * CreateUserDTO validation exception.
 */
public class CreateUserDTONotValidException extends ModelValidationException
{
	/**
	 * CreateUserDTO validation exception constructor.
	 *
	 * @param message exception message
	 */
	public CreateUserDTONotValidException(String message)
	{
		super(message);
	}

	/**
	 * CreateUserDTO validation exception constructor with cause.
	 *
	 * @param message exception message
	 * @param cause   exception cause
	 */
	public CreateUserDTONotValidException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
