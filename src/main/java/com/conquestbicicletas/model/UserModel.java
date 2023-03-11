package com.conquestbicicletas.model;

public class UserModel {

	private String nameUser;
	private String cpf;
	private String email;
	private String password;
	
	
	public UserModel() {
	}
	
	public UserModel(String nameUser, String cpf, String email, String password) {
		this.nameUser = nameUser;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
	}
	
	public String getNameUser() {
		return nameUser;
	}
	
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}	
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
}
