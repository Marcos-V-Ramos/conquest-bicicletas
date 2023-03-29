package com.conquestbicicletas.model.dao;


public class UpdateStatusUserDAO {
	private String cpf;
	private boolean statusUser;
	
	public UpdateStatusUserDAO() {
	}
	
	public UpdateStatusUserDAO(String cpf, boolean statusUser) {
		this.cpf = cpf;
		this.statusUser = statusUser;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public boolean getStatusUser() {
		return statusUser;
	}
	
	public void setStatusUser(boolean statusUser) {
		this.statusUser = statusUser;
	}

}
