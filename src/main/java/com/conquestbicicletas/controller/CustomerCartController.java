package com.conquestbicicletas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "customer/cart", produces = "application/json")
	public ResponseEntity<CustomerCartDAO> getCustomerCart(@RequestParam(value = "customer_id") int customerId){

		CustomerCartDAO getCustomerCart = customerCartService.getCustomerCart(customerId);

		if (getCustomerCart != null) {
			log.info("[INFO] Success in picking up cart");
			return ResponseEntity.status(HttpStatus.OK)
					.body(getCustomerCart);
		}
		log.error("[ERROR] Error when picking up cart");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(null);
	}
	
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

	@PutMapping(value = "customer/cart/updateqtd", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> updateQtdProductCart(@RequestParam(value = "product_id") int productId,
			   														 @RequestParam(value = "customer_id") int customerId, 
			   														 @RequestParam(value = "qtd_product") int qtd){
		boolean updateQtdProduct = customerCartService.updateQtdProductCart(productId, customerId, qtd);

		if (updateQtdProduct == true) {
			log.info("[INFO] Product quantity has been updated");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStatusLogDAO(201, "A quantidade do produto foi alterada com Sucesso!"));
		}
		log.error("[ERROR] It was not possible to update the quantity of the product");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel alterar a quantidade do produto no carrinho"));
	}
	
	@DeleteMapping(value = "customer/cart/remove", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> removeProductCart(@RequestParam(value = "product_id") int productId,
				 											      @RequestParam(value = "customer_id") int customerId){
		boolean removeProduct = customerCartService.removeProductCart(productId, customerId);

		if (removeProduct == true) {
			log.info("[INFO] Product removed from cart successfully");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStatusLogDAO(201, "O produto foi removido do carrinho com sucesso!"));
		}
		log.error("[ERROR] Unable to remove product from cart");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel remover o produto do carrinho"));
	}
	
}
