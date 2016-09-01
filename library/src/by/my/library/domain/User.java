package by.my.library.domain;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int block;
	private String login;
	private String password;
	private String role;
	private String name;
	private String surname;
	private String adress;
	private String passportId;
	

	public User(){}
	
	public User(String login, String password, String role, String name, String surname, String adress,
			String passportId) {
		super();
		//this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.name = name;
		this.surname = surname;
		this.adress = adress;
		this.passportId = passportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	
	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}


}
