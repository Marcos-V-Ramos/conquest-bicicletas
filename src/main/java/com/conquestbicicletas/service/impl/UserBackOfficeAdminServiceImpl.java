package com.conquestbicicletas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.UserBackOfficeAdminRepository;
import com.conquestbicicletas.service.UserBackOfficeAdminService;

@Component
public class UserBackOfficeAdminServiceImpl implements UserBackOfficeAdminService {
	
	@Autowired
	private UserBackOfficeAdminRepository userServiceRepositoryAdmin;
	
	public List<UserBackOfficeDAO> getListUsers() {
		List<UserBackOfficeDAO> listUsers = userServiceRepositoryAdmin.getListUsers();
		return listUsers;
	}	
	
	
	public List<UserBackOfficeDAO> getListUsers(UserBackOfficeDAO requestUserSearch){
		List<UserBackOfficeDAO> listUsersSearch = userServiceRepositoryAdmin.getListUsersSearch(requestUserSearch);
		return listUsersSearch;
	}
	
	public List<UserBackOfficeDAO> filterGroupUser(UserBackOfficeDAO requestTypeGroupUser){
		List<UserBackOfficeDAO> listUsersFilterGroup = userServiceRepositoryAdmin.filterGroupUser(requestTypeGroupUser);
		return listUsersFilterGroup;
	}
	
	public boolean registerUser(UserBackOfficeDAO requestRegisterUser) {
		boolean createUser = userServiceRepositoryAdmin.registerUser(requestRegisterUser);
		return createUser;
	}
	
	
	public boolean updateUser(UserBackOfficeDAO request) {
		boolean isUpdated = userServiceRepositoryAdmin.updateUser(request);
		return isUpdated;
	}
}
