package com.conquestbicicletas.model.dao;

public class InfoCartAuxDAO {

	private int productId;
	private int qtd;
	
	public InfoCartAuxDAO() {
	}

	public InfoCartAuxDAO(int productId, int qtd) {
		this.productId = productId;
		this.qtd = qtd;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
}
