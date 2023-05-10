package com.conquestbicicletas.model.dao;

import java.util.List;

public class CustomerCartDAO {

	private int idCart;
	private int userId;
	private double amountCart;
	private char status;
	private List<ProductCartDAO> productCart;
	
	public CustomerCartDAO() {
	}

	public CustomerCartDAO(int idCart, int userId, double amountCart, char status, List<ProductCartDAO> productCart) {
		this.idCart = idCart;
		this.userId = userId;
		this.amountCart = amountCart;
		this.status = status;
		this.productCart = productCart;
	}

	public int getIdCart() {
		return idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getAmountCart() {
		return amountCart;
	}

	public void setAmountCart(double amountCart) {
		this.amountCart = amountCart;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public List<ProductCartDAO> getProductCart() {
		return productCart;
	}

	public void setProductCart(List<ProductCartDAO> productCart) {
		this.productCart = productCart;
	}

}
