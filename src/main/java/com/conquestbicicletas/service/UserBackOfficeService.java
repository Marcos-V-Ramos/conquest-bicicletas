package com.conquestbicicletas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.UpdateStatusUserDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;

@Service
public interface UserBackOfficeService {
	List<UserBackOfficeDAO> getListUser();
	List<UserBackOfficeDAO> getListUser(UserBackOfficeDAO requestUserSearch);
	List<UserBackOfficeDAO> filterGroupUser(int typeGroup);
	boolean updateUser(UserBackOfficeDAO requestUpdateUser);
	boolean registerUser(UserBackOfficeDAO requestRegisterUser);
	boolean updateStatusUser(UpdateStatusUserDAO requestUpdateStatus);
}
