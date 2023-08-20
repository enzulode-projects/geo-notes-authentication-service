package com.enzulode.auth.dao;

import com.enzulode.auth.model.data.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * User data access object contract.
 */
public interface UserDAO
{
	/**
	 * This method stores new user.
	 *
	 * @param user user instance to be stored
	 * @return created user id if creation succeed, -1 otherwise
	 */
	long createUser(User user);

	/**
	 * This method retrieves an amount of stored users.
	 *
	 * @return amount of stored users
	 */
	long count();

	/**
	 * This method retrieves user from the database by id.
	 *
	 * @param id user id
	 * @return an optional user instance
	 */
	Optional<User> findById(long id);

	/**
	 * This method retrieves user from the database by nickname.
	 *
	 * @param nickname user nickname
	 * @return an optional user instance
	 */
	Optional<User> findByNickname(String nickname);

	/**
	 * This method provides pagination on users.
	 *
	 * @param page pageable instance
	 * @return a page of users
	 */
	Page<User> findUsersForPage(Pageable page);

	/**
	 * This method updates stored user.
	 *
	 * @param user new user data
	 * @return true if update succeed, false otherwise
	 */
	boolean updateUser(User user);

	/**
	 * This method deletes user from the database.
	 *
	 * @param id user id
	 * @return true if user deletion succeed, false otherwise
	 */
	boolean deleteUser(long id);
}
