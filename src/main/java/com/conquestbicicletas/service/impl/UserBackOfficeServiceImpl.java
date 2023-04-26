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

	public UserBackOfficeDAO getUser(int userId) {
		UserBackOfficeDAO user = userBackOfficeRepository.getUser(userId);
		return user;
	}
	
	public List<UserBackOfficeDAO> getListUser() {
		List<UserBackOfficeDAO> listUsers = userBackOfficeRepository.getListUser();
		return listUsers;
	}

	public List<UserBackOfficeDAO> getListUser(UserBackOfficeDAO requestUserSearch) {
		List<UserBackOfficeDAO> listUsersSearch = userBackOfficeRepository.getListUserSearch(requestUserSearch);
		return listUsersSearch;
	}

	public List<UserBackOfficeDAO> filterGroupUser(int typeGroup) {
		List<UserBackOfficeDAO> listUsersFilterGroup = userBackOfficeRepository.filterGroupUser(typeGroup);
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

	public boolean updateStatusUser(UpdateStatusUserDAO requestUpdateStatus) {
		boolean updateStatusUser = userBackOfficeRepository.updateStatus(requestUpdateStatus);
		return updateStatusUser;
	}
}
