package com.conquestbicicletas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.OrderDAO;
import com.conquestbicicletas.model.dao.OrderDetailsDAO;
import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.repository.CustomerOrderRepository;
import com.conquestbicicletas.repository.ProductRepository;
import com.conquestbicicletas.service.CustomerOrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<OrderDAO> getOrder(int customerId) {
		List<OrderDAO> orders = customerOrderRepository.getOrder(customerId);
		return orders;
	}

	public OrderDAO getDetailsOrder(long orderId) {
		OrderDAO order = customerOrderRepository.getOrderDetails(orderId);
		return order;
	}

	public Long addOrder(OrderDAO order) {

		List<Long> verifyIdItemOrder = new ArrayList<>();
		
		if (order.getCustomerId() != 0 && !order.getItemOrder().isEmpty()) {
			Long idOrder = customerOrderRepository.addOrder(order);

			for (OrderDetailsDAO orderDetails : order.getItemOrder()) {
				ProductModelDAO quantityStock = productRepository.visualizeProduct(orderDetails.getProductId());
				if (quantityStock.getProductQuantity() > orderDetails.getProductQtd()) {
					Long idOrderItem = customerOrderRepository.addOrderItem(orderDetails, idOrder);
					
					if(idOrderItem != null) {
						verifyIdItemOrder.add(idOrder);
					}
					
				}else {
					log.error("[ERROR] Unable to add product to order ID_PRODUCT = " + orderDetails.getProductId());
				}
			}			
			if(verifyIdItemOrder.size() != order.getItemOrder().size()) {
				log.info("[INFO] Unable to add all products to order");
				return idOrder;
			}
			return idOrder;	
		}
		return null;
	}
}