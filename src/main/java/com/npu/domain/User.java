package com.npu.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class User implements RowMapper<User> {
	String firstName;
	String lastName;
	String emailId;
	String passcode;
	String user_id;
	String street;
	String aptNo;
	String city;
	String state;
	int zip;

	public User() {

	}

	public User(String firstName, String lastName, String emailId,
			String passcode, String user_id, String street, String aptNo,
			String city, String state, int zip) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.passcode = passcode;
		this.user_id = user_id;
		this.street = street;
		this.aptNo = aptNo;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUserId(String user_id) {
		this.user_id = user_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAptNo() {
		return aptNo;
	}

	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();

		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmailId(rs.getString("email_id"));
		user.setUserId(rs.getString("user_id"));
		user.setStreet(rs.getString("street"));
		user.setAptNo(rs.getString("apt_no"));
		user.setCity(rs.getString("city"));
		user.setState(rs.getString("state"));
		user.setZip(rs.getInt("zip"));
		user.setPasscode(rs.getString("passcode"));
		
		
		return user;

	}

}
