package com.conquestbicicletas.service;


import org.springframework.stereotype.Service;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;

@Service
public interface UserBackOfficeService {
	boolean registerUser(UserBackOfficeDAO requestUser);
	boolean updateStatusUser(UserBackOfficeDAO request);
	boolean updateUser(UserBackOfficeDAO request);
}
