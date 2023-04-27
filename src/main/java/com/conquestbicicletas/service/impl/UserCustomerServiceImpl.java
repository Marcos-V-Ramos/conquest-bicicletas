package com.conquestbicicletas.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.UserCustomerAddressDAO;
import com.conquestbicicletas.model.dao.UserCustomerDAO;
import com.conquestbicicletas.repository.UserCustomerRepository;
import com.conquestbicicletas.service.UserCustomerService;

@Component
public class UserCustomerServiceImpl implements UserCustomerService {

	@Autowired
	private UserCustomerRepository userCustomerRepository;

	public UserCustomerDAO getCustomer(int userId) {
		UserCustomerDAO customer = userCustomerRepository.getCustomer(userId);
		if (customer != null) {
			customer.setUserAddress(userCustomerRepository.getAllAddressCustomer(userId));
			return customer;
		}
		return customer;
	}

	public boolean registerCustomer(UserCustomerDAO requestRegisterCustomer) {
		Integer idCustomerRegister = userCustomerRepository.registerCustomer(requestRegisterCustomer);

		if (idCustomerRegister != null) {
			for (UserCustomerAddressDAO eachAddress : requestRegisterCustomer.getUserAddress()) {
				eachAddress.setUserId(idCustomerRegister);
				userCustomerRepository.registerAddress(eachAddress);
			}

			return true;
		}
		return false;
	}

	public boolean updateCustomer(UserCustomerDAO requestUpdateCustomer) {
		boolean isUpdated = userCustomerRepository.updateCustomer(requestUpdateCustomer);
		return isUpdated;
	}

	public boolean registerAddress(UserCustomerAddressDAO requestRegisterAddress) {
		boolean isRegister = userCustomerRepository.registerAddress(requestRegisterAddress);
		return isRegister;
	}

	public boolean disableAddress(int addressId) {
		boolean isDisable = userCustomerRepository.disableAddress(addressId);
		return isDisable;
	}

	public List<UserCustomerAddressDAO> getAllAddressCustomer(int userId) {
		List<UserCustomerAddressDAO> response = userCustomerRepository.getAllAddressCustomer(userId);
		return response;
	}
}
