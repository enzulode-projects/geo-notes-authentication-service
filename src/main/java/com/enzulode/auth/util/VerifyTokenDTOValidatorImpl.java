package com.enzulode.auth.util;


import com.enzulode.auth.model.dto.VerifyTokenDTO;
import org.springframework.stereotype.Component;

/**
 * VerifyTokenDTO model validator implementation.
 */
@Component
public class VerifyTokenDTOValidatorImpl implements ModelValidator<VerifyTokenDTO>
{
	/**
	 * This method performs model validation.
	 *
	 * @param model validating model instance
	 * @return if validation succeed - true, false otherwise
	 */
	@Override
	public boolean validate(VerifyTokenDTO model)
	{
		if (model == null)
			return false;

		return model.token() != null && !model.token().isBlank();
	}
}
