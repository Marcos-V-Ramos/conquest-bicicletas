package com.conquestbicicletas.service;

import org.springframework.stereotype.Service;

@Service
public interface CustomerCartService {

	boolean addProductCart(int productId, int customerId, int qtd);
}
