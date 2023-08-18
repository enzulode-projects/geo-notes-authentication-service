package com.enzulode.auth.util;

import com.enzulode.auth.model.data.User;
import org.springframework.stereotype.Component;

/**
 * User model validator implementation.
 */
@Component
public class UserValidatorImpl implements ModelValidator<User>
{

	/**
	 * This method performs model validation.
	 *
	 * @param model validating model instance
	 * @return if validation succeed - true, false otherwise
	 */
	@Override
	public boolean validate(User model)
	{
//		Validating model is not null
		if (model == null)
			return false;

//		Validating user id
		if (model.getId() == null || model.getId() < 1)
			return false;

//		Validating user nickname
		if (model.getNickname() == null || model.getNickname().isBlank())
			return false;

//		Validating user password
		if (model.getPassword() == null || model.getPassword().isBlank())
			return false;

//		Validating user creation date
		return model.getCreationDate() != null;
	}
}
