package com.enzulode.auth.model.data;

import lombok.*;

import java.util.Date;

/**
 * This class represents application user.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User implements Comparable<User>
{
	/**
	 * Unique user identifier.
	 */
	private Long id;

	/**
	 * Unique user nickname.
	 */
	private String nickname;

	/**
	 * User password.
	 */
	private String password;

	/**
	 * User creation date.
	 */
	private Date creationDate;

	/**
	 * Compares this object to another one.
	 *
	 * @param user the user to be compared
	 * @return comparison result
	 */
	@Override
	public int compareTo(User user)
	{
		if (user == null)
			return 1;

		if (this.id.longValue() == user.getId().longValue())
			return 0;

		return (this.id > user.getId()) ? 1 : -1;
	}
}
