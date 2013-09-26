package com.epam.edu.rentcar.db.entity;

public class User extends AbstractEntity {

	public final static String UPDATE = "Update users Set email='%s', userpassword='%s', nickname='%s', firstname='%s', lastname='%s', passportnumber='%s' where id=%s";
	public final static String INSERT = "Insert into users (email, userpassword, nickname, firstname, lastname, passportnumber) values ('%s', '%s','%s','%s','%s','%s')";
	public final static String GET_BY_EMAIL = "Select * from users where email='%s'";
	public final static String GET_BY_PASSPORT="Select * from users where passportnumber='%s'";
	
	public final static String TABLE_NAME="users";
	public final static String ID = "id";
	public final static String EMAIL = "email";
	public final static String PASSWORD = "userpassword";
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
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passport = passport;
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
