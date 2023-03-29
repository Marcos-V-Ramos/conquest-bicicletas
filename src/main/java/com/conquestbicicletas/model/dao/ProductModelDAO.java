package com.conquestbicicletas.model.dao;

import java.util.List;

public class ProductModelDAO {

	private int productId;
	private String productName;
	private String productDescription;
	private int productQuantity;
	private double productValue;
	private double productReview; /// Ver para mudar a avaliação para int.
	private boolean productStatus;	
	private List<ImageProductModelDAO> productImages;
	
	public ProductModelDAO() {
	}

	public ProductModelDAO(int productId, String productName, String productDescription,
			int productQuantity, double productValue, double productReview, boolean productStatus, List<ImageProductModelDAO> productImages) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productQuantity = productQuantity;
		this.productValue = productValue;
		this.productReview = productReview;
		this.productStatus = productStatus;
		this.productImages = productImages;
	}
	
	public ProductModelDAO(int productId, String productName, String productDescription,
			int productQuantity, double productValue, double productReview, boolean productStatus) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productQuantity = productQuantity;
		this.productValue = productValue;
		this.productReview = productReview;
		this.productStatus = productStatus;
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

	public boolean getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public List<ImageProductModelDAO> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ImageProductModelDAO> productImages) {
		this.productImages = productImages;
	}
	
}
