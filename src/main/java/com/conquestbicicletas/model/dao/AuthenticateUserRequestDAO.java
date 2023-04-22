package com.conquestbicicletas.model.dao;

public class AuthenticateUserRequestDAO {
    private String userEmail;
    private String userPassword;
    
    
	public AuthenticateUserRequestDAO() {
	}

	public AuthenticateUserRequestDAO(String userEmail, String userPassword) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
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
