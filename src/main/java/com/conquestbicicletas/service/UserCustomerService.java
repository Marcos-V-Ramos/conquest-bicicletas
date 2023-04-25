package com.conquestbicicletas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.UserCustomerAdressDAO;
import com.conquestbicicletas.model.dao.UserCustomerDAO;

@Service
public interface UserCustomerService {

	boolean updateCustomer(UserCustomerDAO requestUpdateCustomer);
	boolean registerAdress(UserCustomerAdressDAO requestRegisterAdress);
	boolean disableAdress(int adressId);
  List<UserCustomerAdressDAO> getAllAdressCustomer(int userId);
	boolean encherto();

}
