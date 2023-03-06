package com.conquestbicicletas.model;

public class Usuario {

	private String nomeUsuario;
	private String email;
	private Boolean status;
	private String senha;
	private int grupo;
	private String cpf;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Usuario(String nomeUsuario, String email, Boolean status, String senha, int grupo, String cpf) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.status = status;
		this.senha = senha;
		this.grupo = grupo;
		this.cpf = cpf;
	}

	public Usuario() {
	}

	@Override
	public String toString() {
		return "Usuario [nomeUsuario=" + nomeUsuario + ", email=" + email + ", status=" + status + ", senha=" + senha
				+ ", grupo=" + grupo + ", cpf=" + cpf + "]";
	}
}
