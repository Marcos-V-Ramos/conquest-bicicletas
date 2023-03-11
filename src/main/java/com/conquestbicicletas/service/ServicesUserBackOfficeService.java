package com.conquestbicicletas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;

@Service
public interface ServicesUserBackOfficeService {
	List<UserBackOfficeDAO> getListUsers();
	List<UserBackOfficeDAO> getListUsers(UserBackOfficeDAO request);	
}
