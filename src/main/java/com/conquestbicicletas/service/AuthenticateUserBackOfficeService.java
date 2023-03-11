package com.conquestbicicletas.service;

import org.springframework.stereotype.Service;

import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;

@Service
public interface AuthenticateUserBackOfficeService {
	AuthenticateUserBackOfficeResponseDAO authenticateUserBackOffice(AuthenticateUserBackOfficeRequestDAO request);
}
