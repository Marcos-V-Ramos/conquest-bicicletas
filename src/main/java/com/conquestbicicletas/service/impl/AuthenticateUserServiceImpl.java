package com.conquestbicicletas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conquestbicicletas.model.dao.AuthenticateUserCustomerResponseDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;
import com.conquestbicicletas.repository.AuthenticateUserRepository;
import com.conquestbicicletas.service.AuthenticateUserService;

@Component
public class AuthenticateUserServiceImpl implements AuthenticateUserService {

	@Autowired
	private AuthenticateUserRepository authenticateRepository;

	public AuthenticateUserBackOfficeResponseDAO authenticateUserBackOffice(AuthenticateUserRequestDAO requestLoginUser) {
		if (requestLoginUser.getUserEmail() != null && requestLoginUser.getUserPassword() != null) {
			try {
				return authenticateRepository.authenticateUserBackOffice(requestLoginUser);
			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		return null;
	}

	public AuthenticateUserCustomerResponseDAO authenticateUserCustomer(AuthenticateUserRequestDAO requestLoginCustomer) {
		if (requestLoginCustomer.getUserEmail() != null && requestLoginCustomer.getUserPassword() != null) {
			try {
				return authenticateRepository.authenticateCustomer(requestLoginCustomer);
			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		return null;
	}
}
