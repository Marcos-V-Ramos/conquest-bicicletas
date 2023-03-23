package com.conquestbicicletas.model.dao;

import com.conquestbicicletas.model.ProductModel;

public class ProductDAO extends ProductModel {

	private int productId;
	private double productReview; /// Ver para mudar a avaliação para int.
	private Boolean status;

	public ProductDAO() {
		super();
	}

	public ProductDAO(int productId, double productReview, Boolean status) {
		super();
		this.productId = productId;
		this.productReview = productReview;
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getProductReview() {
		return productReview;
	}

	public void setProductReview(double productReview) {
		this.productReview = productReview;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
