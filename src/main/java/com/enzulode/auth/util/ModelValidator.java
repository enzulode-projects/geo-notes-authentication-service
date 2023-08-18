package com.enzulode.auth.util;

/**
 * An abstraction for model validators.
 *
 * @param <T> validating model type
 */
public interface ModelValidator<T>
{
	/**
	 * This method performs model validation.
	 *
	 * @param model validating model instance
	 * @return if validation succeed - true, false otherwise
	 */
	boolean validate(T model);
}
