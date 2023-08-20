package com.enzulode.auth.model.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Custom security user details implementation.
 */
public class CustomUserDetailsImpl implements UserDetails
{
	/**
	 * User nickname.
	 */
	private final String nickname;

	/**
	 * User password.
	 */
	private final String password;

	/**
	 * CustomUserDetailsImpl constructor.
	 *
	 * @param user application user model instance
	 */
	public CustomUserDetailsImpl(User user)
	{
		this.nickname = user.getNickname();
		this.password = user.getPassword();
	}

	/**
	 * Returns the authorities granted to the user. Cannot return {@code null}.
	 *
	 * @return the authorities, sorted by natural key (never {@code null})
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return Collections.emptyList();
	}

	/**
	 * Returns the password used to authenticate the user.
	 *
	 * @return the password
	 */
	@Override
	public String getPassword()
	{
		return password;
	}

	/**
	 * Returns the username used to authenticate the user. Cannot return
	 * {@code null}.
	 *
	 * @return the username (never {@code null})
	 */
	@Override
	public String getUsername()
	{
		return nickname;
	}

	/**
	 * Indicates whether the user's account has expired. An expired account cannot be
	 * authenticated.
	 *
	 * @return {@code true} if the user's account is valid (ie non-expired),
	 * {@code false} if no longer valid (ie expired)
	 */
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 *
	 * @return {@code true} if the user is not locked, {@code false} otherwise
	 */
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 *
	 * @return {@code true} if the user's credentials are valid (ie non-expired),
	 * {@code false} if no longer valid (ie expired)
	 */
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot be
	 * authenticated.
	 *
	 * @return {@code true} if the user is enabled, {@code false} otherwise
	 */
	@Override
	public boolean isEnabled()
	{
		return true;
	}
}
