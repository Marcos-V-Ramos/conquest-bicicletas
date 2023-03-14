package com.conquestbicicletas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.ServicesUserBackOfficeRepository;
import com.conquestbicicletas.service.ServicesUserBackOfficeService;

@Component
public class ServicesUserBackOfficeServiceImpl implements ServicesUserBackOfficeService {
	
	@Autowired
	private ServicesUserBackOfficeRepository userServiceRepository;
	
	public List<UserBackOfficeDAO> getListUsers() {
		List<UserBackOfficeDAO> listUsers = userServiceRepository.getListUsers();
		return listUsers;
	}	
	
	
	public List<UserBackOfficeDAO> getListUsers(UserBackOfficeDAO requestUserSearch){
		List<UserBackOfficeDAO> listUsersSearch = userServiceRepository.getListUsersSearch(requestUserSearch);
		return listUsersSearch;
	}
	
	public List<UserBackOfficeDAO> filterGroupUser(UserBackOfficeDAO requestTypeGroupUser){
		List<UserBackOfficeDAO> listUsersFilterGroup = userServiceRepository.filterGroupUser(requestTypeGroupUser);
		return listUsersFilterGroup;
	}
}
