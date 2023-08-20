package com.enzulode.auth.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT token helper implementation.
 */
@Component
public class JwtTokenHelperImpl implements TokenHelper
{

	/**
	 * JWT token secret.
	 */
	private static final String SECRET = "Ykhubk1tRWRKT2NnY2VuWUdVRXNGb2xFRGVGSkFyY29zcld6ZVd3TnN3WGZZVkVnY0Z2WWtRZFJCVHpSbmdwVlJoQWxVY3llZ2JhTVlEVHhHZGNhd1Z4VU1qSVFGcFV2SVR5ZVRVSURvbFlhYmtTa0tnS3FURFF1ZUNNd1B2SlJ3WUxRTmFtaXpqdW9VdlZ4dGp1Y2tDaXdUeWFHdURYYVdidXRXY0J6Q0pCdEpPSmFwbk1pbm9BQkR0YlVSRFRSYU9XZ1lHR1JheFFBUEdMWU5MalVDUWZHUFZXR3VSUEZXdVRQaGFNbWl1cGVPVHdrelhqUml1VmhPUXN1YlRIaA==";

	/**
	 * This method validates token.
	 *
	 * @param token token being validated
	 */
	@Override
	public void verifyToken(String token) throws SignatureException
	{
		Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token);
	}

	/**
	 * This method generates a token.
	 *
	 * @param issuer token issuer username
	 * @return generated token
	 */
	@Override
	public String generateToken(String issuer)
	{
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, issuer);
	}

	/**
	 * Internal method for JWT token generation.
	 *
	 * @param claims JWT token claims
	 * @param issuer token issuer
	 * @return JWT token
	 */
	private String createToken(Map<String, Object> claims, String issuer)
	{
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(issuer)
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(30))))
				.signWith(
						getSignKey(),
						SignatureAlgorithm.HS256
				)
				.compact();
	}

	/**
	 * This method generates a sign SHA key for a token.
	 *
	 * @return token sign key
	 */
	private Key getSignKey()
	{
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
