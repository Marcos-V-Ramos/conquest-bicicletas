package com.conquestbicicletas.model.dao;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ImageProductModelDAO {

	private int idImage;
	private Base64 imageBase64;
	@JsonIgnore
	private int productId;
	
	public ImageProductModelDAO() {
	}

	public ImageProductModelDAO(int idImage, Base64 imageBase64, int idProduct) {
		this.idImage = idImage;
		this.imageBase64 = imageBase64;
		this.productId = idProduct;
	}
	
	public Base64 getImageBase64() {
		return imageBase64;
	}
	
	public void setImageBase64(Base64 imageBase64) {
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
		
}
