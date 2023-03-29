package com.conquestbicicletas.model.dao;

public class UpdateStatusProductDAO {
	private int productId;
	private boolean statusProduct;
	
	public UpdateStatusProductDAO() {
	}
	
	public UpdateStatusProductDAO(int productId, boolean statusProduct) {
		this.productId = productId;
		this.statusProduct = statusProduct;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean getStatusProduct() {
		return statusProduct;
	}

	public void setStatusProduct(boolean statusProduct) {
		this.statusProduct = statusProduct;
	}
	
}
