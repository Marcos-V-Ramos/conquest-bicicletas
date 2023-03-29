package com.conquestbicicletas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.ImageProductModelDAO;
import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.model.dao.UpdateStatusProductDAO;
import com.conquestbicicletas.repository.ProductRepository;
import com.conquestbicicletas.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductRepository ProductRepository;

	public List<ProductModelDAO> getAllListProduct() {
		List<ProductModelDAO> listProduct = ProductRepository.getAllListProduct();
		return listProduct;
	}

	public ProductModelDAO visualizeProduct(int productId) {
		ProductModelDAO product = ProductRepository.visualizeProduct(productId);

		if (product != null) {
			List<ImageProductModelDAO> imgProduct = ProductRepository.visualizeProductImage(productId);
			product.setProductImages(imgProduct);
			return product;
		}
		
		return null;
	}


	public List<ProductModelDAO> getListProductSearch(ProductModelDAO requestProductSearchName) {
		List<ProductModelDAO> listProductSearchName = ProductRepository
				.getListProductSearch(requestProductSearchName);
		return listProductSearchName;
	}

	
	public boolean registerProduct(ProductModelDAO requestRegisterProduct) {
		Integer idProductRegister = ProductRepository.registerProduct(requestRegisterProduct);

		if (idProductRegister != null) {
			for (ImageProductModelDAO eachImage : requestRegisterProduct.getProductImages()) {
				eachImage.setIdProduct(idProductRegister);
				ProductRepository.registerImage(eachImage);
			}

			return true;
		}
		return false;
	}

	
	public boolean updateProduct(ProductModelDAO requestUpdateProduct) {
		boolean isUpdatedProduct = ProductRepository.updateProduct(requestUpdateProduct);
		return isUpdatedProduct;
	}


	public boolean updateStatusProduct(UpdateStatusProductDAO requestUpdateStatusProduct) {
		boolean isUpdatedStatusProduct = ProductRepository.updateStatusProduct(requestUpdateStatusProduct);
		return isUpdatedStatusProduct;
	}

}
