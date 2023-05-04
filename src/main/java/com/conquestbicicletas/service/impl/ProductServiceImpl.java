package com.conquestbicicletas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.ImageProductModelDAO;
import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.model.dao.UpdateStatusProductDAO;
import com.conquestbicicletas.repository.ProductRepository;
import com.conquestbicicletas.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductRepository productRepository;

	public List<ProductModelDAO> getAllListProduct() {
		List<ProductModelDAO> listProduct = productRepository.getAllListProduct();
		return listProduct;
	}

	public List<ProductModelDAO> visualizeListAllProduct() {
		List<ProductModelDAO> listProduct = productRepository.getAllListProduct();
		List<ProductModelDAO> detailsProduct = new ArrayList<>();
		for (ProductModelDAO product : listProduct) {
			List<ImageProductModelDAO> images = productRepository.visualizeProductImage(product.getProductId());
			if (images != null) {
				product.setProductImages(images);
				detailsProduct.add(product);
			} else {
				log.info("[INFO] There are no images for the product");
				product.setProductImages(null);
			}
		}
		return detailsProduct;
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

	public List<ProductModelDAO> getProductSearch(ProductModelDAO requestProductSearchName) {
		List<ProductModelDAO> listProductSearchName = productRepository.getListProductSearch(requestProductSearchName);
		List<ProductModelDAO> detailsProduct = new ArrayList<>();
		for (ProductModelDAO product : listProductSearchName) {
			List<ImageProductModelDAO> images = productRepository.visualizeProductImage(product.getProductId());
			if (images != null) {
				product.setProductImages(images);
				detailsProduct.add(product);
			} else {
				log.info("[INFO] There are no images for the product");
				product.setProductImages(null);
			}
		}
		return detailsProduct;
	}

	public List<ProductModelDAO> getListProductSearch(ProductModelDAO requestProductSearchName) {
		List<ProductModelDAO> listProductSearchName = productRepository.getListProductSearch(requestProductSearchName);
		return listProductSearchName;
	}

	public boolean registerProduct(ProductModelDAO requestRegisterProduct) {
		Integer idProductRegister = productRepository.registerProduct(requestRegisterProduct);

		if (idProductRegister != null) {
			for (ImageProductModelDAO eachImage : requestRegisterProduct.getProductImages()) {
				eachImage.setProductId(idProductRegister);
				productRepository.registerImage(eachImage);
			}

			return true;
		}
		return false;
	}

	public boolean updateProduct(ProductModelDAO requestUpdateProduct) {
		boolean isUpdatedProduct = productRepository.updateProduct(requestUpdateProduct);

		if (isUpdatedProduct) {
			List<ImageProductModelDAO> imagesRegistered = productRepository
					.visualizeProductImage(requestUpdateProduct.getProductId());
			List<ImageProductModelDAO> imagesRequested = requestUpdateProduct.getProductImages();
			if (imagesRegistered.size() == 0) {
				for (ImageProductModelDAO image : imagesRequested) {
					try {
						image.setProductId(requestUpdateProduct.getProductId());
						productRepository.registerImage(image);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				for (ImageProductModelDAO newImage : imagesRequested) {
					for (ImageProductModelDAO image : imagesRegistered) {
						if (image.getProductId() != newImage.getProductId()) {
							try {
								newImage.setProductId(requestUpdateProduct.getProductId());
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

	public boolean deleteImage(int idImage) {
		boolean isDeleteImage = productRepository.deleteImage(idImage);
		if (isDeleteImage) {
			return true;
		}
		return false;
	}

}
