package com.conquestbicicletas.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.OrderDAO;

@Service
public interface CustomerOrderService {
	List<OrderDAO> getOrder(int customerId);
	OrderDAO getDetailsOrder(long orderId);
	Long addOrder(OrderDAO order);	
}
