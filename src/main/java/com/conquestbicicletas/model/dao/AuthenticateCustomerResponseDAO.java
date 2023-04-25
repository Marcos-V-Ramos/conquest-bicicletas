package com.conquestbicicletas.model.dao;

public class AuthenticateCustomerResponseDAO {

	private int customerId;

	public AuthenticateCustomerResponseDAO() {
		super();
	}

	public AuthenticateCustomerResponseDAO(int customerId) {
		super();
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
