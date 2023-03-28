package com.conquestbicicletas.model.dao;

import com.conquestbicicletas.model.UserModel;

public class UserBackOfficeDAO extends UserModel {
	private int group;
	private boolean status;
	
	public UserBackOfficeDAO() {
	}
	
	public UserBackOfficeDAO(String nameUser,String cpf, String email, String password, boolean status, int group) {
		super(nameUser, cpf, email, password);
		this.group = group;
		this.status = status;
		
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
