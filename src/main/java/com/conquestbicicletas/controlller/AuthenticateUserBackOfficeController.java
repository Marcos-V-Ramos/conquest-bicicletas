package com.conquestbicicletas.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;
import com.conquestbicicletas.service.AuthenticateUserBackOfficeService;

@RestController
@RequestMapping("/conquest")
public class AuthenticateUserBackOfficeController {
	
	@Autowired
	private AuthenticateUserBackOfficeService authenticateUserBackOfficeService;

	@PostMapping(value = "/user/loginbackoffice", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuthenticateUserBackOfficeResponseDAO> login(@RequestBody AuthenticateUserBackOfficeRequestDAO requestLogin) {
		
		AuthenticateUserBackOfficeResponseDAO response = authenticateUserBackOfficeService.authenticateUserBackOffice(requestLogin);
		
		if(response != null) {
			return  ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthenticateUserBackOfficeResponseDAO());
	}
	
}
