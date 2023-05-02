package com.conquestbicicletas.model.dao;

public class ProductCartDAO {

	private ProductModelDAO product;
	private int qtdProduct;
	
	public ProductCartDAO() {
	}

	public ProductCartDAO(ProductModelDAO product, int qtdProduct) {
		this.product = product;
		this.qtdProduct = qtdProduct;
	}

	public ProductModelDAO getProduct() {
		return product;
	}

	public void setProduct(ProductModelDAO product) {
		this.product = product;
	}

	public int getQtdProduct() {
		return qtdProduct;
	}

	public void setQtdProduct(int qtdProduct) {
		this.qtdProduct = qtdProduct;
	}

}
