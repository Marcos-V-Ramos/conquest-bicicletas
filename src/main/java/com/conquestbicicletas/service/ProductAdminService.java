package com.conquestbicicletas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.ProductModel;
import com.conquestbicicletas.model.dao.ProductDAO;

@Service
public interface ProductAdminService {
	List<ProductModel> getListProduct();
	List<ProductModel> getListProductSearchName(ProductModel requestProductSearchName);
	boolean updateProduct(ProductDAO request); // Faltam as imagens 

}
