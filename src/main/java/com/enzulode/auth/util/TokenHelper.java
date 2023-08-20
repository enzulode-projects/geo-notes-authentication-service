package com.enzulode.auth.util;

/**
 * Token helper contract.
 */
public interface TokenHelper
{
	/**
	 * This method validates token.
	 *
	 * @param token token being validated
	 */
	void verifyToken(String token);

	/**
	 * This method generates a token.
	 *
	 * @param issuer token issuer username
	 * @return generated token
	 */
	String generateToken(String issuer);

}
