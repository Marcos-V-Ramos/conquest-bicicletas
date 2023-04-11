package com.conquestbicicletas.model.dao;

public class AuthenticateUserBackOfficeResponseDAO {
	private int idUser;
	private int group;
	

	public AuthenticateUserBackOfficeResponseDAO() {
	}

	public AuthenticateUserBackOfficeResponseDAO(int idUser, int group) {
		this.idUser = idUser;
		this.group = group;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getGrupo() {
		return group;
	}

	public void setGrupo(int group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "AuthenticateUserBackOfficeResponseDAO [idUser=" + idUser + ", group=" + group + "]";
	}
	
}
