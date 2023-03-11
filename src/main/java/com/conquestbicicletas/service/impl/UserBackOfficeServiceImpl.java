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

	public boolean registerUser(UserBackOfficeDAO requestUser) {
		boolean createUser = userRepository.registerUser(requestUser);
		return createUser;
    
	public boolean updateStatusUser(UserBackOfficeDAO request) {
		boolean updateStatus = userRepository.updateStatusUser(request);
		return updateStatus;

	}
}
