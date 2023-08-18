package com.enzulode.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main authentication service class.
 */
@SpringBootApplication
public class GeoNotesAuthenticationServiceApplication
{

	/**
	 * Authentication service entry point.
	 *
	 * @param args cli arguments
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(GeoNotesAuthenticationServiceApplication.class, args);
	}

}
