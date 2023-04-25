package com.conquestbicicletas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.AuthenticateUserCustomerResponseDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;
import com.conquestbicicletas.service.AuthenticateUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/conquest")
public class AuthenticateUserController {

	@Autowired
	private AuthenticateUserService authenticateUserService;

	/**
	 * Recebe email e senha do usuario via Json do front-end
	 * 
	 * @param requestLoginUser
	 * @return O grupo que o usuario pertence
	 */
	@PostMapping(value = "/user/loginbackoffice", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthenticateUserBackOfficeResponseDAO> loginBackoffice(
			@RequestBody AuthenticateUserRequestDAO requestLoginUser) {

		AuthenticateUserBackOfficeResponseDAO response = authenticateUserService.authenticateUserBackOffice(requestLoginUser);

		if (response != null) {
			log.info("[INFO] Login successful");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}
		log.error("[ERROR] Error when logging in");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AuthenticateUserBackOfficeResponseDAO());
	}

	@PostMapping(value = "/customer/logincustomer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthenticateUserCustomerResponseDAO> loginCustomer(
			@RequestBody AuthenticateUserRequestDAO requestLoginCustomer) {

		AuthenticateUserCustomerResponseDAO response = authenticateUserService
				.authenticateUserCustomer(requestLoginCustomer);

		if (response != null) {
			log.info("[INFO] Login successful");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}
		log.error("[ERROR] Error when logging in");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AuthenticateUserCustomerResponseDAO());
	}

}
