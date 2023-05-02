package com.conquestbicicletas.model.dao;

import java.util.List;

public class CustomerCartDAO {

	private int userId;
	private List<ProductCartDAO> productCart;

	public CustomerCartDAO() {
	}

	public CustomerCartDAO(int userId, List<ProductCartDAO> productCart) {
		this.userId = userId;
		this.productCart = productCart;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public List<ProductCartDAO> getProductCart() {
		return productCart;
	}
	
	public void setProductCart(List<ProductCartDAO> productCart) {
		this.productCart = productCart;
	}
	
}
