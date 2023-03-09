package com.conquestbicicletas.model.dao;

import com.conquestbicicletas.model.UserModel;

public class UserBackOfficeDAO extends UserModel {
	private int group;
	private Boolean status;
	
	public UserBackOfficeDAO() {
	}
	
	public UserBackOfficeDAO(String nameUser,String cpf, String email, String password, Boolean status, int group) {
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
