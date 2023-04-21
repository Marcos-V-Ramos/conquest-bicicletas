package com.conquestbicicletas.model.dao;

public class UpdateStatusProductDAO {
	private int productId;
	private boolean productStatus;
	
	public UpdateStatusProductDAO() {
	}
	
	public UpdateStatusProductDAO(int productId, boolean productStatus) {
		this.productId = productId;
		this.productStatus = productStatus;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}
	
}
