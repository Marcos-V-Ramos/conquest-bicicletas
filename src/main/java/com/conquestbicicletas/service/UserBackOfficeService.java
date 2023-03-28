package com.conquestbicicletas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.UpdateStatusUserDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;

@Service
public interface UserBackOfficeService {
	List<UserBackOfficeDAO> getListUsers();
	List<UserBackOfficeDAO> getListUsers(UserBackOfficeDAO requestUserSearch);
	List<UserBackOfficeDAO> filterGroupUser(UserBackOfficeDAO requestTypeGroupUser);
	boolean updateUser(UserBackOfficeDAO requestUpdateUser);
	boolean registerUser(UserBackOfficeDAO requestRegisterUser);
	boolean updateStatusUser(UpdateStatusUserDAO requestUpdateStatus);
}
