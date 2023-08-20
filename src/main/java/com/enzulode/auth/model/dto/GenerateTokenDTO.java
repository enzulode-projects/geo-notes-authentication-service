package com.enzulode.auth.model.dto;

/**
 * This DTO contains all required information to generate new token.
 *
 * @param nickname user nickname
 */
public record GenerateTokenDTO(String nickname, String password)
{
}
