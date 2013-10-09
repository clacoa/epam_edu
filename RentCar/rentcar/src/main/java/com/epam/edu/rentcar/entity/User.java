package com.epam.edu.rentcar.entity;

import org.apache.log4j.Logger;


public class User extends AbstractEntity {

	private static Logger LOG = Logger.getLogger(User.class);

	private String email;
	private String password;
	private String nickName;
	private String firstName;
	private String lastName;
	private String passport;
	private Status role;
	
	public User(){
		
	}

	public User(Long id, String email, String password, String nickName,
			String firstName, String lastName, String passport, Status role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
		this.setRole(role);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public Status getRole() {
		return role;
	}

	public void setRole(Status role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password
				+ ", nickName=" + nickName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", passport=" + passport
				+ ",  " + role.toString() + "]";
	}
		
}
