package com.conquestbicicletas.model.dao;


public class OrderDetailsDAO {

	private long idItemOrder;
	private int productQtd;
	private int productId;
	private String productName;
	private double productValue;
	private ImageProductModelDAO productImage;
	
	public OrderDetailsDAO() {
	}

	public OrderDetailsDAO(long idItemOrder, int productQtd, int productId, String productName, double productValue,
			ImageProductModelDAO productImage) {
		this.idItemOrder = idItemOrder;
		this.productQtd = productQtd;
		this.productId = productId;
		this.productName = productName;
		this.productValue = productValue;
		this.productImage = productImage;
	}

	public long getIdItemOrder() {
		return idItemOrder;
	}

	public void setIdItemOrder(long idItemOrder) {
		this.idItemOrder = idItemOrder;
	}

	public int getProductQtd() {
		return productQtd;
	}

	public void setProductQtd(int productQtd) {
		this.productQtd = productQtd;
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

	public double getProductValue() {
		return productValue;
	}

	public void setProductValue(double productValue) {
		this.productValue = productValue;
	}

	public ImageProductModelDAO getProductImage() {
		return productImage;
	}

	public void setProductImage(ImageProductModelDAO productImage) {
		this.productImage = productImage;
	}

}
