package com.conquestbicicletas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.CustomerCartDAO;
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

		if (product.getProductQuantity() > qtd) {
			boolean addCart = customerCartRepository.addProductCart(productId, customerId, qtd);
			if (addCart) {
				return addCart;
			}
		}
		log.error("[ERROR] Unable to add producer to cart");
		return false;
	}
}
