package com.conquestbicicletas.model.dao;


public class ProductCartDAO {

	private int idItemCart;
	private int productQtd;
	private int productId;
	private String productName;
	private double productValue;
	private double productReview; /// Ver para mudar a avaliação para int.	
	private ImageProductModelDAO productImage;
	
	public ProductCartDAO() {
	}

	public ProductCartDAO(int idItemCart, int productqtd, int productId, String productName, double productValue,
			double productReview, ImageProductModelDAO productImage) {
		this.idItemCart = idItemCart;
		this.productQtd = productqtd;
		this.productId = productId;
		this.productName = productName;
		this.productValue = productValue;
		this.productReview = productReview;
		this.productImage = productImage;
	}

	public int getIdItemCart() {
		return idItemCart;
	}

	public void setIdItemCart(int idItemCart) {
		this.idItemCart = idItemCart;
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

	public double getProductReview() {
		return productReview;
	}

	public void setProductReview(double productReview) {
		this.productReview = productReview;
	}

	public ImageProductModelDAO getProductImage() {
		return productImage;
	}

	public void setProductImage(ImageProductModelDAO productImage) {
		this.productImage = productImage;
	}

}
