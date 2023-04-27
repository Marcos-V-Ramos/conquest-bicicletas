package com.conquestbicicletas.model.dao;

public class UserCustomerAddressDAO {

	private int userId;
	private int addressId;
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String complemento;
	private int numero;
	private boolean status;
	private boolean isAddressCustomer;

	public UserCustomerAddressDAO() {
	}

	public UserCustomerAddressDAO(int userId, int addressId, String cep, String logradouro, String bairro,
			String localidade, String uf, String complemento, int numero, boolean status, boolean isAddressCustomer) {
		super();
		this.userId = userId;
		this.addressId = addressId;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.complemento = complemento;
		this.numero = numero;
		this.status = status;
		this.isAddressCustomer = isAddressCustomer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isAddressCustomer() {
		return isAddressCustomer;
	}

	public void setAddressCustomer(boolean isAddressCustomer) {
		this.isAddressCustomer = isAddressCustomer;
	}

}
