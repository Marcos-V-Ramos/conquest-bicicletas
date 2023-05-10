package com.conquestbicicletas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.CustomerCartDAO;
import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.repository.CustomerCartRepository;
import com.conquestbicicletas.repository.ProductRepository;
import com.conquestbicicletas.service.CustomerCartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerCartServiceImpl implements CustomerCartService {

	@Autowired
	private CustomerCartRepository customerCartRepository;

	@Autowired
	private ProductRepository productRepository;

	public boolean addProductCart(int productId, int customerId, int qtd) {

		ProductModelDAO product = productRepository.visualizeProduct(productId);

		if (qtd < product.getProductQuantity() && qtd != 0) {
			boolean addProductCart = customerCartRepository.addProductCart(productId, customerId, qtd);
			return addProductCart;
		}
		log.error("[ERROR] Unable to add producer to cart");
		return false;
	}

	public CustomerCartDAO getCustomerCart(int customerId) {
		Integer idCart = customerCartRepository.verifyCart(customerId);
		if (idCart != 0 && idCart != null) {
			CustomerCartDAO cart = customerCartRepository.getItemsCart(customerId);
			return cart;
		} else if (idCart == 0 && idCart != null) {
			Integer addCart = customerCartRepository.addCart(customerId);
			if (addCart != 0 && addCart != null) {
				CustomerCartDAO cart = customerCartRepository.getItemsCart(customerId);
				return cart;
			}
		}
		return null;
	}

	public boolean updateQtdProductCart(int idCart, int productId, int qtd) {

		ProductModelDAO product = productRepository.visualizeProduct(productId);

		if (qtd > 0 && qtd < product.getProductQuantity()) {
			boolean updateQtdCart = customerCartRepository.updateQtdProductCart(idCart, productId, qtd);

			if (updateQtdCart) {
				return true;
			}
		}
		return false;
	}

	public boolean removeProductCart(int productId, int cartId) {

		boolean updateQtdCart = customerCartRepository.removeProductCart(productId, cartId);

		if (updateQtdCart) {
			return true;
		}
		return false;
	}
}