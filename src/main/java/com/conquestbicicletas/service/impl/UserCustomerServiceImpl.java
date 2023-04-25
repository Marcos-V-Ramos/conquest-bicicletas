package com.conquestbicicletas.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.UserCustomerAdressDAO;
import com.conquestbicicletas.model.dao.UserCustomerDAO;
import com.conquestbicicletas.repository.UserCustomerRepository;
import com.conquestbicicletas.service.UserCustomerService;

import lombok.extern.slf4j.Slf4j;


@Component
public class UserCustomerServiceImpl implements UserCustomerService{
	
	@Autowired
	private UserCustomerRepository userCustomerRepository;
	

	public boolean registerCustomer(UserCustomerDAO requestRegisterCustomer) {
		Integer idCustomerRegister = userCustomerRepository.registerCustomer(requestRegisterCustomer);

		if (idCustomerRegister != null) {
			for (UserCustomerAdressDAO eachAdress : requestRegisterCustomer.getUserAdress()) {
				eachAdress.setUserId(idCustomerRegister);
				userCustomerRepository.registerAdress(eachAdress);
			}

			return true;
		}
		return false;
	}


	public boolean updateCustomer(UserCustomerDAO requestUpdateCustomer) {
		boolean isUpdated = userCustomerRepository.updateCustomer(requestUpdateCustomer);
		return isUpdated;
	}

	public boolean registerAdress(UserCustomerAdressDAO requestRegisterAdress) {
		boolean isRegister = userCustomerRepository.registerAdress(requestRegisterAdress);
		return isRegister;
	}

	public boolean disableAdress(int adressId) {
		boolean isDisable = userCustomerRepository.disableAdress(adressId);
		return isDisable;
    
	public boolean encherto() {
		return false;
	}

	public List<UserCustomerAdressDAO> getAllAdressCustomer(int userId) {
		List<UserCustomerAdressDAO> response = userCustomerRepository.getAllAdressCustomer(userId);
		return response;
	}
}
