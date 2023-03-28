package com.conquestbicicletas.model.dao;

import java.util.ArrayList;

public class ProductModelDAO {

	private int productId;
	private String productName;
	private String productDescription;
	private int productQuantity;
	private double productValue;
	private double productReview; /// Ver para mudar a avaliação para int.
	private boolean status;	
	private ArrayList<ImageProductModelDAO> productImage;
	
	public ProductModelDAO() {
	}

	public ProductModelDAO(int productId, String productName, String productDescription,
			int productQuantity, double productValue, double productReview, boolean status, ArrayList<ImageProductModelDAO> productImage) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productQuantity = productQuantity;
		this.productValue = productValue;
		this.productReview = productReview;
		this.status = status;
		this.productImage = productImage;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getProductValue() {
		return productValue;
	}

	public void setProductValue(double productValue) {
		this.productValue = productValue;
	}

	public double getProductReview() {
		return productReview;
	}

	public void setProductReview(double productReview) {
		this.productReview = productReview;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ArrayList<ImageProductModelDAO> getProductImage() {
		return productImage;
	}

	public void setProductImage(ArrayList<ImageProductModelDAO> productImage) {
		this.productImage = productImage;
	}
	
}
