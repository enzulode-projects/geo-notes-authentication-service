package com.enzulode.auth.util;

import com.enzulode.auth.model.dto.CreateUserDTO;
import org.springframework.stereotype.Component;

/**
 * CreateUserDTO model validator implementation.
 */
@Component
public class CreateUserDTOValidatorImpl implements ModelValidator<CreateUserDTO>
{

	/**
	 * This method performs model validation.
	 *
	 * @param model validating model instance
	 * @return if validation succeed - true, false otherwise
	 */
	@Override
	public boolean validate(CreateUserDTO model)
	{
		if (model == null)
			return false;

		if (model.nickname() == null || model.nickname().isBlank())
			return false;

		return model.password() != null && !model.password().isBlank();
	}
}
