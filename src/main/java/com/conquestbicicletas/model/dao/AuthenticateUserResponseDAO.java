package com.conquestbicicletas.model.dao;

public class AuthenticateUserResponseDAO {
	private int userId;
	private int userGroup;

	
	public AuthenticateUserResponseDAO() {
	}

	public AuthenticateUserResponseDAO(int userId, int userGroup) {
		this.userId = userId;
		this.userGroup = userGroup;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

}
