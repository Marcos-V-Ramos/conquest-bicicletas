package com.conquestbicicletas.model.dao;

import com.conquestbicicletas.model.UserModel;

public class UserBackOfficeDAO extends UserModel {
	private int userGroup;
	private boolean userStatus;

	public UserBackOfficeDAO() {
	}

	public UserBackOfficeDAO(int userId, String userName, String userCpf, String userEmail, String userPassword,
			int userGroup, boolean userStatus) {
		super(userId, userName, userCpf, userEmail, userPassword);
		this.userGroup = userGroup;
		this.userStatus = userStatus;
	}

	public int getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

}
