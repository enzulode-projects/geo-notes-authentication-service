package com.enzulode.auth.model.dto;

/**
 * This DTO contains all required information to verify existing token.
 *
 * @param token token
 */
public record VerifyTokenDTO(String token)
{
}
