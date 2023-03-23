package com.conquestbicicletas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.ProductModel;
import com.conquestbicicletas.model.dao.ProductDAO;
import com.conquestbicicletas.repository.ProductAdminRepository;
import com.conquestbicicletas.service.ProductAdminService;

@Component
public class ProductAdminServiceImpl implements ProductAdminService {
	
	@Autowired
	public ProductAdminRepository userServiceRepositoryProduct;

	@Override
	public List<ProductModel> getListProduct() {
		List<ProductModel> listProduct = userServiceRepositoryProduct.getListProduct();
		return listProduct;
	}

	@Override
	public List<ProductModel> getListProductSearchName(ProductModel requestProductSearchName) {
		List<ProductModel> listProductSearchName = userServiceRepositoryProduct.getListProductSearchName(requestProductSearchName);
		return listProductSearchName;
	}

	@Override
	public boolean updateProduct(ProductDAO request) {
		boolean isUpdatedProduct = userServiceRepositoryProduct.updateProduct(request);
		return isUpdatedProduct;
	}

}
