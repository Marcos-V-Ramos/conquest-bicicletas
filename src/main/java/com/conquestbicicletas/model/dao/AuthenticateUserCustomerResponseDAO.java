package com.conquestbicicletas.model.dao;

public class AuthenticateUserCustomerResponseDAO {

	private int customerId;

	public AuthenticateUserCustomerResponseDAO() {
		super();
	}

	public AuthenticateUserCustomerResponseDAO(int customerId) {
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
