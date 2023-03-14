package com.conquestbicicletas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.UserBackOfficeRepository;
import com.conquestbicicletas.service.UserBackOfficeService;

@Component
public class UserBackOfficeServiceImpl implements UserBackOfficeService {

	@Autowired
	private UserBackOfficeRepository userRepository;

	public boolean registerUser(UserBackOfficeDAO requestRegisterUser) {
		boolean createUser = userRepository.registerUser(requestRegisterUser);
		return createUser;
	}
	
	
	//TODO realizar testes.
	public boolean updateUser(UserBackOfficeDAO request) {
		boolean isUpdated = userRepository.updateUser(request);
		return isUpdated;
	}
}
