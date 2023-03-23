package com.conquestbicicletas.model;

public class ProductModel {

	private String productCode;
	private String productName;
	private String productDescription;
	private int productQuantity;
	private double productValue;
	private int productImage;
	
	public ProductModel() {
		super();
	}

	public ProductModel(String productCode, String productName, String productDescription, int productQuantity,
			double productValue, int productImage) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productQuantity = productQuantity;
		this.productValue = productValue;
		this.productImage = productImage;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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

	public int getProductImage() {
		return productImage;
	}

	public void setProductImage(int productImage) {
		this.productImage = productImage;
	}

}
