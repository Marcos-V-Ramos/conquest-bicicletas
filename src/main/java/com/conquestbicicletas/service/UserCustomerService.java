package com.conquestbicicletas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.conquestbicicletas.model.dao.UserCustomerAddressDAO;
import com.conquestbicicletas.model.dao.UserCustomerDAO;

@Service
public interface UserCustomerService {
	UserCustomerDAO getCustomer(int userId);
	boolean registerCustomer(UserCustomerDAO requestRegisterCustomer);
	boolean updateCustomer(UserCustomerDAO requestUpdateCustomer);
	boolean registerAddress(UserCustomerAddressDAO requestRegisterAddress);
	boolean disableAddress(int addressId);
	List<UserCustomerAddressDAO> getAllAddressCustomer(int userId);
}
