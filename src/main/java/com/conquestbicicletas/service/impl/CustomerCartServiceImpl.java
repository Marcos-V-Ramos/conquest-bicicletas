package com.conquestbicicletas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.CustomerCartDAO;
import com.conquestbicicletas.model.dao.InfoCartAuxDAO;
import com.conquestbicicletas.model.dao.ProductCartDAO;
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
		Boolean verifyCart = customerCartRepository.verifyProductCart(productId, customerId);
		if (qtd > 0) {
			if (product.getProductQuantity() > qtd && verifyCart == false && verifyCart != null) {
				boolean addCart = customerCartRepository.addProductCart(productId, customerId, qtd);
				if (addCart) {
					return addCart;
				}
			} else if (product.getProductQuantity() > qtd && verifyCart == true && verifyCart != null) {
				boolean updateQtdCart = customerCartRepository.updateQtdProductCart(productId, customerId, qtd);
				if (updateQtdCart) {
					return updateQtdCart;
				}
			}
		}
		log.error("[ERROR] Unable to add producer to cart");
		return false;
	}

	public CustomerCartDAO getCustomerCart(int customerId) {
		
		List<ProductCartDAO> listProdCart = new ArrayList<>();
		List<InfoCartAuxDAO> productCart = customerCartRepository.getProductCartCustomer(customerId);
		CustomerCartDAO customerCart = new CustomerCartDAO();

		if (!productCart.isEmpty()) {
			for (InfoCartAuxDAO infoCart : productCart) {
				ProductCartDAO cart = new ProductCartDAO();
				ProductModelDAO product = productRepository.visualizeProduct(infoCart.getProductId());
				cart.setProduct(product);
				cart.setQtdProduct(infoCart.getQtd());
				listProdCart.add(cart);
			}
			customerCart.setUserId(customerId);
			customerCart.setProductCart(listProdCart);
			return customerCart;
		}
		return null;
	}

	public boolean updateQtdProductCart(int productId, int customerId, int qtd) {
		
		if (qtd > 0) {
			boolean updateQtdCart = customerCartRepository.updateQtdProductCart(productId, customerId, qtd);

			if (updateQtdCart) {
				return true;
			}
		}
		return false;
	}

	public boolean removeProductCart(int productId, int customerId) {
		
		boolean updateQtdCart = customerCartRepository.removeProductCart(productId, customerId);

		if (updateQtdCart) {
			return true;
		}
		return false;
	}
}