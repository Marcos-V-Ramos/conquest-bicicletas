package com.conquestbicicletas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.UpdateStatusUserDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.UserBackOfficeRepository;
import com.conquestbicicletas.service.UserBackOfficeService;

@Component
public class UserBackOfficeServiceImpl implements UserBackOfficeService {
	
	@Autowired
	private UserBackOfficeRepository userBackOfficeRepository;
	
	public List<UserBackOfficeDAO> getListUsers() {
		List<UserBackOfficeDAO> listUsers = userBackOfficeRepository.getListUsers();
		return listUsers;
	}	
	
	
	public List<UserBackOfficeDAO> getListUsers(UserBackOfficeDAO requestUserSearch){
		List<UserBackOfficeDAO> listUsersSearch = userBackOfficeRepository.getListUsersSearch(requestUserSearch);
		return listUsersSearch;
	}
	
	public List<UserBackOfficeDAO> filterGroupUser(UserBackOfficeDAO requestTypeGroupUser){
		List<UserBackOfficeDAO> listUsersFilterGroup = userBackOfficeRepository.filterGroupUser(requestTypeGroupUser);
		return listUsersFilterGroup;
	}
	
	public boolean registerUser(UserBackOfficeDAO requestRegisterUser) {
		boolean createUser = userBackOfficeRepository.registerUser(requestRegisterUser);
		return createUser;
	}
	
	
	public boolean updateUser(UserBackOfficeDAO request) {
		boolean isUpdated = userBackOfficeRepository.updateUser(request);
		return isUpdated;
	}
	
	@Override
	public boolean updateStatusUser(UpdateStatusUserDAO requestUpdateStatus) {
		boolean updateStatusUser = userBackOfficeRepository.updateStatus(requestUpdateStatus);
		return updateStatusUser;
	}
}
