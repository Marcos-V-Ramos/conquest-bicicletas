package com.conquestbicicletas.service;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.UserCustomerDAO;

@Service
public interface UserCustomerService {
	public boolean registerCustomer(UserCustomerDAO requestRegisterCustomer);

}
