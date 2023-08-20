package com.enzulode.auth.model.dto;

/**
 * This DTO contains all required information to create new user.
 *
 * @param nickname user nickname
 * @param password user password
 */
public record CreateUserDTO(String nickname, String password)
{
}
