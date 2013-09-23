package com.epam.edu.rentcar.db.entity;

public class User extends AbstractEntity {

	public final static String GET_BY_ID = "Select * from users where id=%s";
	public final static String GET_ALL="Select * from users";
	
	public final static String ID = "id";
	public final static String EMAIL = "email";
	public final static String USER_PASSWORD = "userpassword";
	public final static String NICKNAME = "nickname";
	public final static String FIRSTNAME = "firstname";
	public final static String LASTNAME = "lastname";
	public final static String PASSPORT = "passportnumber";
	
	private String email;
	private String password;
	private String nickName;
	private String firstName;
	private String lastName;
	private String passport;

	public User(Long id, String email, String password, String nickName,
			String firstName, String lastName, String passport) {
		this.email=email;
		this.password=password;
		this.nickName=nickName;
		this.firstName=firstName;
		this.lastName=lastName;
		this.passport=passport;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password
				+ ", nickName=" + nickName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", passport=" + passport + "]";
	}
}
