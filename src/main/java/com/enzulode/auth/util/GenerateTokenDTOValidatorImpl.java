package com.enzulode.auth.util;

import com.enzulode.auth.model.dto.GenerateTokenDTO;
import org.springframework.stereotype.Component;

/**
 * GenerateTokenDTO model validator implementation.
 */
@Component
public class GenerateTokenDTOValidatorImpl implements ModelValidator<GenerateTokenDTO>
{

	/**
	 * This method performs model validation.
	 *
	 * @param model validating model instance
	 * @return if validation succeed - true, false otherwise
	 */
	@Override
	public boolean validate(GenerateTokenDTO model)
	{
		if (model == null)
			return false;

		return model.nickname() != null && !model.nickname().isBlank();
	}
}
