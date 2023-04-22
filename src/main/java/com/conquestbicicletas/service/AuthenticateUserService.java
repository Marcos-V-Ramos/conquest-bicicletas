package com.conquestbicicletas.service;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.AuthenticateCustomerResponseDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserResponseDAO;

@Service
public interface AuthenticateUserService {
	AuthenticateUserResponseDAO authenticateUserBackOffice(AuthenticateUserRequestDAO requestLoginUser);
	AuthenticateCustomerResponseDAO authenticateUserCustomer(AuthenticateUserRequestDAO requestLoginCustomer);
}
