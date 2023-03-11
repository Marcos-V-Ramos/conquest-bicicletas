package com.conquestbicicletas.model.dao;

public class AuthenticateUserBackOfficeResponseDAO {
	private int group;

	public AuthenticateUserBackOfficeResponseDAO() {
	}

	public AuthenticateUserBackOfficeResponseDAO(int group) {
		this.group = group;
	}

	public int getGrupo() {
		return group;
	}

	public void setGrupo(int group) {
		this.group = group;
	}
}
