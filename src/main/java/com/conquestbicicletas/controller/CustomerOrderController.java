package com.conquestbicicletas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.OrderDAO;
import com.conquestbicicletas.service.CustomerOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/conquest")
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService customerOrderService;

	/**
	 * Pega todos os pedidos que um cliente fez.
	 * 
	 * @param customerId
	 * @return
	 */
	@GetMapping(value = "customer/order", produces = "application/json")
	public ResponseEntity<List<OrderDAO>> getOrder(@RequestParam(value = "customer_id") int customerId) {

		List<OrderDAO> getCustomerOrders = customerOrderService.getOrder(customerId);

		if (getCustomerOrders != null && !getCustomerOrders.isEmpty()) {
			log.info("[INFO] Success in picking up orders");
			return ResponseEntity.status(HttpStatus.OK).body(getCustomerOrders);
		} else if (getCustomerOrders != null && !getCustomerOrders.isEmpty()) {
			log.info("[INFO] Success in picking up orders and empty list");
			return ResponseEntity.status(HttpStatus.OK).body(getCustomerOrders);
		}
		log.error("[ERROR] Error when picking up order");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/*
	 * Pega todos os detalhes de um unico pedido
	 */
	@GetMapping(value = "customer/order/detail", produces = "application/json")
	public ResponseEntity<OrderDAO> getDetailsOrder(@RequestParam(value = "order_id") long orderId) {

		OrderDAO getOrderDetails = customerOrderService.getDetailsOrder(orderId);

		if (getOrderDetails != null) {
			log.info("[INFO] Success in picking up order details");
			return ResponseEntity.status(HttpStatus.OK).body(getOrderDetails);
		}
		log.error("[ERROR] Error when picking up order details");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	

	/**
	 * 
	 * @param productId
	 * @param customerId
	 * @param qtd
	 * @return 
	 * @return
	 */
	@PostMapping(value = "customer/order/add", produces = "application/json")
	public ResponseEntity<Long> addOrder(@RequestBody OrderDAO order) {

		Long addOrder = customerOrderService.addOrder(order);

		if (addOrder != null) {
			log.info("[INFO] Order relized sucess");
			return ResponseEntity.status(HttpStatus.OK)
					.body(addOrder);
		}
		log.error("[ERROR] Unable to place order");
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(addOrder);

	}
}
