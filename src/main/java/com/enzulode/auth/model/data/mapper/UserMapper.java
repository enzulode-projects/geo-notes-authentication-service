package com.enzulode.auth.model.data.mapper;

import com.enzulode.auth.model.data.User;
import com.enzulode.auth.util.ModelValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * User model mapper.
 */
@Component
@RequiredArgsConstructor
public class UserMapper implements RowMapper<User>
{

	/**
	 * User model validator instance.
	 */
	private final ModelValidator<User> userValidator;

	/**
	 * This method maps database rows into java objects and validates it.
	 *
	 * @param rs the {@code ResultSet} to map (pre-initialized for the current row)
	 * @param rowNum the number of the current row
	 * @return a {@link User} instance
	 * @throws SQLException something went wrong during data fetching from database row
	 */
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setNickname(rs.getString("nickname"));
		user.setPassword(rs.getString("password"));
		user.setCreationDate(Date.from(rs.getTimestamp("creation_date").toInstant()));

		return (userValidator.validate(user)) ? user : null;
	}
}
