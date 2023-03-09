package com.conquestbicicletas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.UserBackOfficeRepository;
import com.conquestbicicletas.service.UserBackOfficeService;

@Component
public class UserBackOfficeServiceImpl implements UserBackOfficeService{

	@Autowired
	private UserBackOfficeRepository userRepository;
	
	public List<UserBackOfficeDAO> getListUsers() {
		List<UserBackOfficeDAO> listUsers = userRepository.getListUsers();
		return listUsers;
	}	
}
