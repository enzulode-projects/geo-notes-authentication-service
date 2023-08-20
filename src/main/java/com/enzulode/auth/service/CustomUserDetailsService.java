package com.enzulode.auth.service;

import com.enzulode.auth.dao.UserDAO;
import com.enzulode.auth.model.data.CustomUserDetailsImpl;
import com.enzulode.auth.model.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * This class is responsible for user authentication process.
 */
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{

	/**
	 * UserDAO instance.
	 */
	private final UserDAO userDAO;

	/**
	 * Locates the user based on the username.
	 *
	 * @throws UsernameNotFoundException if the user could not be found or the user has no GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException
	{
		Optional<User> user = userDAO.findByNickname(nickname);
		return user
				.map(CustomUserDetailsImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with nickname: " + nickname));
	}
}
