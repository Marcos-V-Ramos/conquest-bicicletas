package com.conquestbicicletas.model.dao;


public class ImageProductModelDAO {

	private int idImage;
	private String imageBase64;
	private int productId;
	
	public ImageProductModelDAO() {
	}

	public ImageProductModelDAO(int idImage, String imageBase64, int idProduct) {
		this.idImage = idImage;
		this.imageBase64 = imageBase64;
		this.productId = idProduct;
	}
	
	public String getImageBase64() {
		return imageBase64;
	}
	
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
	public int getIdProduct() {
		return productId;
	}
	
	public void setIdProduct(int idProduct) {
		this.productId = idProduct;
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	@Override
	public String toString() {
		return "ImageProductModelDAO [idImage=" + idImage + ", imageBase64=" + imageBase64 + ", productId=" + productId
				+ "]";
	}
	
		
}
