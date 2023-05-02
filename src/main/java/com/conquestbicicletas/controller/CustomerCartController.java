package com.conquestbicicletas.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.CustomerCartDAO;
import com.conquestbicicletas.model.dao.ResponseStatusLogDAO;
import com.conquestbicicletas.service.CustomerCartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/conquest")
public class CustomerCartController {

	@Autowired
	private CustomerCartService customerCartService;

	@PostMapping(value = "customer/cart/add", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> addProductCart(@RequestParam(value = "product_id") int productId,
															   @RequestParam(value = "customer_id") int customerId, 
															   @RequestParam(value = "qtd_product") int qtd) {

		boolean addProduct = customerCartService.addProductCart(productId, customerId, qtd);

		if (addProduct == true) {
			log.info("[INFO] Product added to cart");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStatusLogDAO(201, "O Produto foi adicionado ao carrinho com sucesso!"));
		}
		log.error("[ERROR] Unable to add product to cart");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel adicionar o produto ao carrinho"));

	}
}
