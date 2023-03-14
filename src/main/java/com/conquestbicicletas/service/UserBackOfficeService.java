package com.conquestbicicletas.service;


import org.springframework.stereotype.Service;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;

@Service
public interface UserBackOfficeService {
	boolean updateUser(UserBackOfficeDAO requestUpdateUser);
	boolean registerUser(UserBackOfficeDAO requestRegisterUser);
}
