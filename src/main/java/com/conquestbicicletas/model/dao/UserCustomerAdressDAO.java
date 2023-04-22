package com.conquestbicicletas.model.dao;

public class UserCustomerAdressDAO {

	private int userId;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String complemento;
	private String numero;
	private boolean isAdressCustomer;
	
	public UserCustomerAdressDAO() {
	}

	public UserCustomerAdressDAO(int userId, String cep, String logradouro, String bairro, String localidade, String uf,
			String complemento, String numero, boolean isAdressCustomer) {
		super();
		this.userId = userId;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.complemento = complemento;
		this.numero = numero;
		this.isAdressCustomer = isAdressCustomer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean getAdressCustomer() {
		return isAdressCustomer;
	}

	public void setAdressCustomer(boolean isAdressCustomer) {
		this.isAdressCustomer = isAdressCustomer;
	}
	
}
