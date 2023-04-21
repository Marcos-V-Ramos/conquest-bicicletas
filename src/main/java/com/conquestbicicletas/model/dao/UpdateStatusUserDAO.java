package com.conquestbicicletas.model.dao;

public class UpdateStatusUserDAO {
	private int userId;
	private boolean userStatus;

	public UpdateStatusUserDAO() {
	}

	public UpdateStatusUserDAO(int userId, boolean userStatus) {
		this.userId = userId;
		this.userStatus = userStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

}
