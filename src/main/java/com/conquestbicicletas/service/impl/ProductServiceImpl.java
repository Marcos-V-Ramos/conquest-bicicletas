package com.conquestbicicletas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.model.dao.UpdateStatusProductDAO;
import com.conquestbicicletas.repository.ProductRepository;
import com.conquestbicicletas.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	public ProductRepository ProductRepository;

	@Override
	public List<ProductModelDAO> getListProduct() {
		List<ProductModelDAO> listProduct = ProductRepository.getListProduct();
		return listProduct;
	}

	@Override
	public List<ProductModelDAO> getListProductSearchName(ProductModelDAO requestProductSearchName) {
		List<ProductModelDAO> listProductSearchName = ProductRepository.getListProductSearchName(requestProductSearchName);
		return listProductSearchName;
	}

	@Override
	public boolean updateProduct(ProductModelDAO requestUpdateProduct) {
		boolean isUpdatedProduct = ProductRepository.updateProduct(requestUpdateProduct);
		return isUpdatedProduct;
	}
	
	@Override
	public boolean updateStatusProduct(UpdateStatusProductDAO requestUpdateStatusProduct) {
		boolean isUpdatedStatusProduct = ProductRepository.updateStatusProduct(requestUpdateStatusProduct);
		return isUpdatedStatusProduct;
	}
	
	@Override
	public boolean registerProduct(ProductModelDAO requestRegisterProduct) {
		boolean isRegisterProduct = ProductRepository.registerProduct(requestRegisterProduct);
		return isRegisterProduct;
		
	}

}
