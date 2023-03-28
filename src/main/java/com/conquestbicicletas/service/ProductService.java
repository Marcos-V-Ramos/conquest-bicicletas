package com.conquestbicicletas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.model.dao.UpdateStatusProductDAO;

@Service
public interface ProductService {
	List<ProductModelDAO> getListProduct();
	List<ProductModelDAO> getListProductSearchName(ProductModelDAO requestProductSearchName);
	boolean updateProduct(ProductModelDAO requestUpadateProduct); // Faltam as imagens 
	boolean updateStatusProduct(UpdateStatusProductDAO requestUpdateStatusProduct);
	boolean registerProduct(ProductModelDAO requestRegisterProduct);
}
