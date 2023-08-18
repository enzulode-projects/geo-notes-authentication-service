package com.enzulode.auth.testutils;

import com.enzulode.auth.model.data.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * Tests utility class.
 */
public class Utils
{

	/**
	 * Random generator instance.
	 */
	private static final RandomGenerator randomGenerator = new Random();

	/**
	 * This method generates a random string for user generating purposes.
	 *
	 * @return random string
	 */
	public static String generateRandomString(int leftLimit, int rightLimit, int targetSize)
	{
		return randomGenerator.ints(leftLimit, rightLimit + 1)
				.limit(targetSize)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}

	/**
	 * This method generates a list of 20 random users.
	 *
	 * @return a list of random users
	 */
	public static List<User> generateRandomUsers()
	{
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 20; i++)
		{
			StringBuilder sb = new StringBuilder(generateRandomString('a', 'z', 15));
			User user = new User(
					1L,
					sb.toString(),
					sb.reverse().toString(),
					Date.from(Instant.now())
			);
			users.add(user);
		}

		return users;
	}
}
