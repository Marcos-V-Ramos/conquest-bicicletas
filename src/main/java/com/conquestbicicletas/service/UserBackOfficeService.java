package com.conquestbicicletas.service;


import org.springframework.stereotype.Service;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;

@Service
public interface UserBackOfficeService {r
	boolean updateUser(UserBackOfficeDAO request);
	boolean registerUser(UserBackOfficeDAO requestRegisterUser);
	boolean updateStatusUser(UserBackOfficeDAO requestUpdateStatus);
}
