package com.conquestbicicletas.service;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.AuthenticateUserCustomerResponseDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;

@Service
public interface AuthenticateUserService {
	AuthenticateUserBackOfficeResponseDAO authenticateUserBackOffice(AuthenticateUserRequestDAO requestLoginUser);
	AuthenticateUserCustomerResponseDAO authenticateUserCustomer(AuthenticateUserRequestDAO requestLoginCustomer);
}
