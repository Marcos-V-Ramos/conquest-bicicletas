package com.conquestbicicletas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.ResponseStatusLogDAO;
import com.conquestbicicletas.model.dao.UserCustomerAdressDAO;
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

	@GetMapping(value = "/customer", produces = "application/json")
	public ResponseEntity<UserCustomerDAO> getCustomer(@RequestParam(value = "id_customer") int userId) {
		UserCustomerDAO customer = userCustomerService.getCustomer(userId);

		if (customer != null) {
			log.info("[INFO] Success in get customer");
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		}
		log.info("[ERROR] Success in get customer");
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}

	/**
	 * Registra um cliente
	 * 
	 * @param requestRegisteCustomer
	 * @return
	 */
	@PostMapping(value = "/customer/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerCustomer(@RequestBody UserCustomerDAO requestRegisteCustomer) {
		boolean isRegister = userCustomerService.registerCustomer(requestRegisteCustomer);

		if (isRegister) {
			log.info("[INFO] Success in registering customer");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStatusLogDAO(201, "O cliente foi registrado com sucesso!"));
		}
		log.error("[ERROR] Error in registering customer");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o cliente"));
	}

	/**
	 * Solicita o update do cliente
	 * 
	 * @param requestUpdateUser
	 * @return se o usuario foi atualizado ou não
	 */
	@PutMapping(value = "/customer/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> updateCustomer(@RequestBody UserCustomerDAO requestUpdateCustomer) {

		boolean isUpdated = userCustomerService.updateCustomer(requestUpdateCustomer);

		if (isUpdated) {
			log.info("[INFO] Success in updating customer");
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponseStatusLogDAO(202, "O cliente foi alterado com sucesso!"));
		}
		log.error("[ERROR] Error updating customer");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new ResponseStatusLogDAO(406, "Erro ao alterar cliente!"));
	}

	/**
	 * Registra um usuario
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/customer/register/adress", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerAdress(
			@RequestBody UserCustomerAdressDAO requestRegisterAdress) {
		boolean isRegister = userCustomerService.registerAdress(requestRegisterAdress);

		if (isRegister) {
			log.info("[INFO] Success in registering adress");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStatusLogDAO(201, "O endereco foi registrado com sucesso!"));
		}
		log.error("[ERROR] Error in registering adress");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o endereco"));
	}

	/**
	 * Lista todos os enderecos.
	 * 
	 * @param id
	 * @return retorna uma lista de enderecos.
	 */
	@GetMapping(value = "/customer/list/adress", produces = "application/json")
	public ResponseEntity<List<UserCustomerAdressDAO>> getAllAdressCustomer(
			@RequestParam(value = "id_customer") int userId) {

		List<UserCustomerAdressDAO> response = userCustomerService.getAllAdressCustomer(userId);

		if (response != null && response.size() > 0) {
			log.info("[INFO] Success in list adress");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (response.size() == 0) {
			log.info("[INFO] List without content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		log.error("[ERROR] Unable to list adress");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Desativa endereco.
	 * 
	 * @param id
	 * @return retorna uma lista de enderecos.
	 */
	@PutMapping(value = "/customer/disable/adress", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> disableAdress(@RequestParam(value = "id_adress") int adressId) {

		boolean isDisable = userCustomerService.disableAdress(adressId);

		if (isDisable) {
			log.info("[INFO] Success in disable adress");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStatusLogDAO(201, "O endereco foi desativado com sucesso!"));
		}
		log.error("[ERROR] Error in disable adress");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel desativado o endereco"));
	}
}
