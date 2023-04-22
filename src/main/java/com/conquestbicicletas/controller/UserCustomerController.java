package com.conquestbicicletas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.ResponseStatusLogDAO;
import com.conquestbicicletas.model.dao.UserCustomerDAO;
import com.conquestbicicletas.service.UserCustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/conquest")
public class UserCustomerController {
	
	@Autowired
	private UserCustomerService userCustomerService;
	
	/**
	 * Registra um cliente
	 * 
	 * @param requestRegisteCustomer
	 * @return
	 */
	@PostMapping(value = "/backoffice/customer/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerCustomer(@RequestBody UserCustomerDAO requestRegisteCustomer) {
		boolean isRegister = userCustomerService.registerCustomer(requestRegisteCustomer);

		if (isRegister != false) {
			log.info("[INFO] Success in registering customer");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStatusLogDAO(201, "O cliente foi registrado com sucesso!"));
		}
		log.error("[ERROR] Error in registering customer");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o cliente"));
	}

}
