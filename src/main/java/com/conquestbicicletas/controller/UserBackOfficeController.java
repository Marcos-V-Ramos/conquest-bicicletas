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
import com.conquestbicicletas.model.dao.UpdateStatusUserDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.service.UserBackOfficeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/conquest")
public class UserBackOfficeController {

	@Autowired
	private UserBackOfficeService userBackOfficeService;

	/**
	 * Lista todos os usuarios cadastrados no backOffice
	 * 
	 * @return Lista de usuarios
	 */
	@GetMapping(value = "/backoffice/user/list", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> getListAllUsers() {

		List<UserBackOfficeDAO> response = userBackOfficeService.getListUser();

		if (response != null && response.size() > 0) {
			log.info("[INFO] Success in list user");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (response.size() == 0) {
			log.info("[INFO] List without content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		log.error("[ERROR] Unable to list user");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Registra um usuario
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/backoffice/user/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerUser(@RequestBody UserBackOfficeDAO requestRegisterUser) {
		boolean isRegister = userBackOfficeService.registerUser(requestRegisterUser);

		if (isRegister != false) {
			log.info("[INFO] Success in registering user");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStatusLogDAO(201, "O usuario foi registrado com sucesso!"));
		}
		log.error("[ERROR] Error in registering user");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o usuario"));
	}

	/**
	 * Solicita o update de um usuario
	 * 
	 * @param requestUpdateUser
	 * @return se o usuario foi atualizado ou não
	 */
	@PutMapping(value = "/backoffice/user/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> updateUser(@RequestBody UserBackOfficeDAO requestUpdateUser) {

		boolean isUpdated = userBackOfficeService.updateUser(requestUpdateUser);

		if (isUpdated) {
			log.info("[INFO] Success in updating user");
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponseStatusLogDAO(202, "O usuario foi alterado com sucesso!"));
		}
		log.error("[ERROR] Error updating user");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new ResponseStatusLogDAO(406, "Erro ao alterar usuario!"));
	}

	/**
	 * Update status user
	 * 
	 * @param requestUpdateStatus
	 * @return
	 */
	@PutMapping(value = "/backoffice/user/update/status", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UpdateStatusUserDAO> updateStatus(@RequestBody UpdateStatusUserDAO requestUpdateStatusUser) {

		boolean isUpdatedStatus = userBackOfficeService.updateStatusUser(requestUpdateStatusUser);

		if (isUpdatedStatus) {
			log.info("[INFO] Success in updating status user");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestUpdateStatusUser);
		}
		log.error("[ERROR] Error updating status user");
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(requestUpdateStatusUser);
	}

	/**
	 * Filtra o usuário por grupo
	 * 
	 * @param requestTypeGroupUser; tipo de grupo
	 * @return retorna uma lista com o filtro selecionado.
	 */
	@GetMapping(value = "/backoffice/user/filtergroup", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> filterGroupUser(
			@RequestParam(value = "filter_type_group") int typeGroup) {

		List<UserBackOfficeDAO> response = userBackOfficeService.filterGroupUser(typeGroup);

		if (response != null && response.size() > 0) {
			log.info("[INFO] Success in list user");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (response.size() == 0) {
			log.info("[INFO] List without content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		log.error("[ERROR] Unable to list user");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	/**
	 * Pesquisa no banco de dados um determinado usuario por nome, cpf
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@PostMapping(value = "/backoffice/user/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> getListSearchUsers(
			@RequestBody UserBackOfficeDAO requestUserSearch) {

		List<UserBackOfficeDAO> response = userBackOfficeService.getListUser(requestUserSearch);

		if (response != null && response.size() > 0) {
			log.info("[INFO] Success in list user");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (response.size() == 0) {
			log.info("[INFO] List without content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		log.error("[ERROR] Unable to list user");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
