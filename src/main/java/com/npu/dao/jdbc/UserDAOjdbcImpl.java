package com.npu.dao.jdbc;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.npu.dao.UserDAO;
import com.npu.domain.User;
import com.npu.exceptions.UnknownTableEntryException;

@Repository("userDaoJdbc")
@Transactional
public class UserDAOjdbcImpl implements UserDAO {

	private User user;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		user = new User();

	}

	@Override
	// Insert user
	public void insertUser(User user) throws DuplicateKeyException {
		String insertUsrDetailssql = "INSERT INTO user (first_name, last_name, email_id, passcode, "
				+ "user_id, street, apt_no, city, state, zip) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?)";

		jdbcTemplate.update(
				insertUsrDetailssql,
				new Object[] { user.getFirstName(), user.getLastName(),
						user.getEmailId(), user.getPasscode(),
						user.getUser_id(), user.getStreet(), user.getAptNo(),
						user.getCity(), user.getState(), user.getZip() });

	}

	// Update user details based on user id of the user
	public void updateUser(User user) {
		String updateUserDetailsSql = "UPDATE user SET first_name = ?,"
				+ " last_name = ?, email_id = ?,passcode = ?, "
				+ "user_id = ?, street = ?, apt_no = ?,city = ?,state = ?,zip = ?"
				+ " where user_id = ?";

		jdbcTemplate.update(
				updateUserDetailsSql,
				new Object[] { user.getFirstName(), user.getLastName(),
						user.getEmailId(), user.getPasscode(),
						user.getUser_id(), user.getStreet(), user.getAptNo(),
						user.getCity(), user.getState(), user.getZip(),
						user.getUser_id() });

	}

	// Delete user details based on user id of the user
	public void deleteUser(String user_id) throws UnknownTableEntryException {
		String deleteUserDetailsSql = "DELETE  FROM user "
				+ "WHERE user_id = ?";

		jdbcTemplate.update(deleteUserDetailsSql, new Object[] { user_id });

	}

	@Override
	public User getUser(String user_id) {

		String readCurBalSql = "SELECT first_name, last_name, email_id, passcode,"
				+ "user_id, street, apt_no, city, state, zip "
				+ "FROM user "
				+ "WHERE user_id = ?";

		User userFromDb = jdbcTemplate.queryForObject(readCurBalSql, user,
				user_id);
		return userFromDb;

	}

	@Override
	public int getUserCount() {
		String getCountSql = "select count(*) from user";
		return jdbcTemplate.queryForObject(getCountSql, Integer.class);

	}

	@Override
	public User authenticateUser(String userId, String passcode) {

		String readCurBalSql = "SELECT first_name, last_name, email_id, passcode,"
				+ "user_id, street, apt_no, city, state, zip "
				+ "FROM user "
				+ "WHERE user_id = ?" + "AND passcode = ? ";

		User userFromDb = jdbcTemplate.queryForObject(readCurBalSql, user,
				userId, passcode);

		return userFromDb;
	}

}