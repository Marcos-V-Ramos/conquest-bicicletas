package com.conquestbicicletas.model;


public class UserModel {

	private int userId;
	private String userName;
	private String userCpf;
	private String userEmail;
	private String userPassword;
	
		
	public UserModel() {
	}

	public UserModel(int userId, String userName, String userCpf, String userEmail, String userPassword) {
		this.userId = userId;
		this.userName = userName;
		this.userCpf = userCpf;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCpf() {
		return userCpf;
	}
	public void setUserCpf(String userCpf) {
		this.userCpf = userCpf;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
