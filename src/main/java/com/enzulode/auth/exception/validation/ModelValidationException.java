package com.enzulode.auth.exception.validation;

/**
 * Validation exception abstraction.
 */
public abstract class ModelValidationException extends Exception
{

	/**
	 * Model validation exception constructor.
	 *
	 * @param message exception message
	 */
	public ModelValidationException(String message)
	{
		super(message);
	}

	/**
	 * Model validation exception constructor with cause.
	 *
	 * @param message exception message
	 * @param cause exception cause
	 */
	public ModelValidationException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
