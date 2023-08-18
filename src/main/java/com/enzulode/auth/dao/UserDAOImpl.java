package com.enzulode.auth.dao;

import com.enzulode.auth.model.data.User;
import com.enzulode.auth.util.ModelValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * User DAO implementation.
 */
@Component
@Slf4j
public class UserDAOImpl implements UserDAO
{

	/**
	 * JDBCTemplate instance.
	 */
	private final JdbcTemplate jdbcTemplate;

	/**
	 * JDBC User row mapper.
	 */
	private final RowMapper<User> userRowMapper;

	/**
	 * User model validator instance.
	 */
	private final ModelValidator<User> userValidator;

	/**
	 * User DAO constructor.
	 *
	 * @param dataSource application datasource
	 * @param userRowMapper user row mapper
	 */
	public UserDAOImpl(DataSource dataSource, RowMapper<User> userRowMapper, ModelValidator<User> userValidator)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.userRowMapper = userRowMapper;
		this.userValidator = userValidator;
	}

	/**
	 * This method stores new user.
	 *
	 * @param user user instance to be stored
	 * @return created user id if creation succeed, -1 otherwise
	 */
	@Override
	public long createUser(User user)
	{
		if (!userValidator.validate(user))
			return -1;

		try
		{
			KeyHolder keyHolder = new GeneratedKeyHolder();

			boolean result = jdbcTemplate.update((conn) -> {
				PreparedStatement ps = conn
						.prepareStatement(
								"INSERT INTO _user (nickname, password, creation_date) VALUES (?, ?, ?)",
								new String[] { "id" }
						);
				ps.setString(1, user.getNickname());
				ps.setString(2, user.getPassword());
				ps.setTimestamp(3, Timestamp.from(user.getCreationDate().toInstant()));
				return ps;
			}, keyHolder) > 0;

			if (!result || keyHolder.getKey() == null)
				return -1;

			user.setId(keyHolder.getKey().longValue());
			return keyHolder.getKey().longValue();
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during user creation", e);
			return -1;
		}
	}

	/**
	 * This method retrieves user from the database by id.
	 *
	 * @param id user id
	 * @return an optional user instance
	 */
	@Override
	public Optional<User> findById(long id)
	{

		if (id < 1)
			return Optional.empty();

		try
		{
			User user = jdbcTemplate.queryForObject(
					"SELECT * FROM _user WHERE id = ? LIMIT 1",
					userRowMapper,
					id
			);
			return Optional.ofNullable(user);
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during user fetch", e);
			return Optional.empty();
		}
	}

	/**
	 * This method retrieves an amount of stored users.
	 *
	 * @return amount of stored users
	 */
	@Override
	public long count()
	{
		Long amount = jdbcTemplate.queryForObject(
				"SELECT count(*) FROM _user",
				Long.class
		);

		return (amount == null) ? 0 : amount;
	}

	/**
	 * This method provides pagination on users.
	 *
	 * @param page page instance
	 * @return a page of users
	 */
	@Override
	public Page<User> findUsersForPage(Pageable page)
	{
		Order order = (page.getSort().isEmpty())
				? Order.by("id")
				: page.getSort().toList().get(0);

		String sql = "SELECT * FROM _user ORDER BY %s %s LIMIT %d OFFSET %d"
				.formatted(
						order.getProperty(),
						order.getDirection().name(),
						page.getPageSize(),
						page.getOffset()
				);

		List<User> users = jdbcTemplate
				.query(sql, userRowMapper);

		return new PageImpl<>(users, page, count());
	}

	/**
	 * This method updates stored user.
	 *
	 * @param user new user data
	 * @return true if update succeed, false otherwise
	 */
	@Override
	public boolean updateUser(User user)
	{
		try
		{
			return jdbcTemplate
					.update(
							"UPDATE _user SET nickname = ?, password = ? WHERE id = ?",
							user.getNickname(),
							user.getPassword(),
							user.getId()
					) > 0;
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during user update", e);
			return false;
		}
	}

	/**
	 * This method deletes user from the database.
	 *
	 * @param id user id
	 * @return true if user deletion succeed, false otherwise
	 */
	@Override
	public boolean deleteUser(long id)
	{
		try
		{
			return jdbcTemplate
					.update(
							"DELETE FROM _user WHERE id = ?",
							id
					) > 0;
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during user deletion", e);
			return false;
		}
	}
}
