package com.conquestbicicletas.service;


import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.CustomerCartDAO;

@Service
public interface CustomerCartService {
	CustomerCartDAO getCustomerCart(int customerId);
	boolean addProductCart(int productId, int customerId, int qtd);	
	boolean updateQtdProductCart(int productId, int customerId, int qtd);
	boolean removeProductCart(int productId, int customerId);
}
