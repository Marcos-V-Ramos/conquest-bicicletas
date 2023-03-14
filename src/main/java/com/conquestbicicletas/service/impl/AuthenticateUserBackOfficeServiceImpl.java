package com.conquestbicicletas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;
import com.conquestbicicletas.repository.AutenticateUserBackOfficeRepository;
import com.conquestbicicletas.service.AuthenticateUserBackOfficeService;

@Component
public class AuthenticateUserBackOfficeServiceImpl implements  AuthenticateUserBackOfficeService{
	
	@Autowired
	private AutenticateUserBackOfficeRepository authenticateRepository;
	
	public AuthenticateUserBackOfficeResponseDAO authenticateUserBackOffice(AuthenticateUserBackOfficeRequestDAO requestLoginUser) {
		if(requestLoginUser.getEmail() != null && requestLoginUser.getPassword() != null) {
			try {
				return authenticateRepository.authenticateUserBackOffice(requestLoginUser);
			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		return null;
	}
}
