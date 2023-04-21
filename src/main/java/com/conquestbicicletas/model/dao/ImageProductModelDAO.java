package com.conquestbicicletas.model.dao;


public class ImageProductModelDAO {

	private int idImage;
	private int productId;
	private String imageBase64;
	
	
	public ImageProductModelDAO() {
	}

	public ImageProductModelDAO(int idImage, int productId, String imageBase64) {
		this.idImage = idImage;
		this.productId = productId;
		this.imageBase64 = imageBase64;
	}
	
	public String getImageBase64() {
		return imageBase64;
	}
	
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
		
}
