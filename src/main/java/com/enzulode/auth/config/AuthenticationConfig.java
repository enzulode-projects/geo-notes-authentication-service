package com.enzulode.auth.config;

import com.enzulode.auth.dao.UserDAO;
import com.enzulode.auth.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

/**
 * Application authentication configuration.
 */
@Configuration
@EnableWebSecurity
public class AuthenticationConfig
{

	/**
	 * Configuring application authentication manager.
	 *
	 * @param config authentication configuration
	 * @return authentication manager instance
	 * @throws Exception if something went wrong
	 */
	@Bean
	public AuthenticationManager configureAuthenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}

	/**
	 * Configuring application security filter chain.
	 *
	 * @param http http security instance
	 * @return application security filter chain
	 * @throws Exception if something went wrong
	 */
	@Bean
	public SecurityFilterChain configureSecurityFilerChain(HttpSecurity http) throws Exception
	{
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement((customizer) -> customizer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				.authorizeHttpRequests((customizer) -> customizer
						.requestMatchers(
								"/auth/register",
								"/auth/token",
								"/auth/verify"
						).permitAll()
				)
				.exceptionHandling((customizer) -> customizer
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				.build();
	}

	/**
	 * This method configures application password encoder.
	 *
	 * @return password encoder instance
	 */
	@Bean("passwordEncoderBean")
	public PasswordEncoder configurePasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	/**
	 * This method configures custom application user details service.
	 *
	 * @param userDAO user DAO instance
	 * @return custom user details service instance
	 */
	@Bean("customUserDetailsService")
	public UserDetailsService configureCustomUserDetailsService(UserDAO userDAO)
	{
		return new CustomUserDetailsService(userDAO);
	}

	/**
	 * This method configures application authentication provider.
	 *
	 * @param userDetailsService application user details service instance
	 * @param passwordEncoder application password encoder instance
	 * @return application authentication provider instance
	 */
	@Bean("authenticationProvider")
	@DependsOn({"customUserDetailsService", "passwordEncoderBean"})
	public AuthenticationProvider configureAuthenticationProvider(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder
	)
	{
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
}
