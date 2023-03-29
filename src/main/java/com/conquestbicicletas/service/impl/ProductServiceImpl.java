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
	public ProductRepository productRepository;

	public List<ProductModelDAO> getAllListProduct() {
		List<ProductModelDAO> listProduct = productRepository.getAllListProduct();
		return listProduct;
	}

	public ProductModelDAO visualizeProduct(int productId) {
		ProductModelDAO product = productRepository.visualizeProduct(productId);

		if (product != null) {
			List<ImageProductModelDAO> imgProduct = productRepository.visualizeProductImage(productId);
			product.setProductImages(imgProduct);
			return product;
		}

		return null;
	}

	public List<ProductModelDAO> getListProductSearch(ProductModelDAO requestProductSearchName) {
		List<ProductModelDAO> listProductSearchName = productRepository.getListProductSearch(requestProductSearchName);
		return listProductSearchName;
	}

	public boolean registerProduct(ProductModelDAO requestRegisterProduct) {
		Integer idProductRegister = productRepository.registerProduct(requestRegisterProduct);

		if (idProductRegister != null) {
			for (ImageProductModelDAO eachImage : requestRegisterProduct.getProductImages()) {
				eachImage.setIdProduct(idProductRegister);
				productRepository.registerImage(eachImage);
			}

			return true;
		}
		return false;
	}

	public boolean updateProduct(ProductModelDAO requestUpdateProduct) {
		boolean isUpdatedProduct = productRepository.updateProduct(requestUpdateProduct);

		if (isUpdatedProduct) {
			List<ImageProductModelDAO> images = productRepository
					.visualizeProductImage(requestUpdateProduct.getProductId());

			if (images.size() == 0) {
				for (ImageProductModelDAO image : requestUpdateProduct.getProductImages()) {
					try {
						image.setIdProduct(requestUpdateProduct.getProductId());
						productRepository.registerImage(image);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				for (ImageProductModelDAO newImage : requestUpdateProduct.getProductImages()) {
					for (ImageProductModelDAO image : images) {
						if (image.getIdProduct() != newImage.getIdProduct()) {
							try {
								newImage.setIdProduct(requestUpdateProduct.getProductId());
								productRepository.registerImage(newImage);
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}
					}
				}
			}

		}

		return isUpdatedProduct;
	}

	public boolean updateStatusProduct(UpdateStatusProductDAO requestUpdateStatusProduct) {
		boolean isUpdatedStatusProduct = productRepository.updateStatusProduct(requestUpdateStatusProduct);
		return isUpdatedStatusProduct;
	}

}
