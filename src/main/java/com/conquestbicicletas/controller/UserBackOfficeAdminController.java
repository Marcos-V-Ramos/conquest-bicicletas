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
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.ResponseStatusLogDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.service.UserBackOfficeAdminService;


@RestController
@CrossOrigin(value="*")
@RequestMapping("/conquest")
public class UserBackOfficeAdminController {

	@Autowired
	private UserBackOfficeAdminService userServiceAdmin;
	 
	
	/**
	 * Lista todos os usuarios cadastrados no backOffice
	 * 
	 * @return Lista de usuarios
	 */
	@GetMapping(value = "/user/adm", produces = "application/json")
    public ResponseEntity<List<UserBackOfficeDAO>> getListAllUsers() {
    	
		List<UserBackOfficeDAO> response = userServiceAdmin.getListUsers();
    	
    	if (response != null) {
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

	
	
	/**
	 * Pesquisa no banco de dados um determinado usuario por nome, cpf
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@PostMapping(value = "/user/adm/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> getListSearchUsers(@RequestBody UserBackOfficeDAO requestUserSearch){
		
		List<UserBackOfficeDAO> response = userServiceAdmin.getListUsers(requestUserSearch);
		
		if(response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	
	
	
	/**
	 * Filtra o usuário por grupo
	 * 
	 * @param requestTypeGroupUser; tipo de grupo
	 * @return retorna uma lista com o filtro selecionado.
	 */
	@PostMapping(value = "/user/adm/filtergroup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> filterGroupUser(@RequestBody UserBackOfficeDAO requestTypeGroupUser){
		
		List<UserBackOfficeDAO> response = userServiceAdmin.filterGroupUser(requestTypeGroupUser);
		
		if(response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	
	
	
	/**
	 * Registra um usuario
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/user/adm/registeruser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerUser(@RequestBody UserBackOfficeDAO requestRegisterUser){
		boolean isRegister =  userServiceAdmin.registerUser(requestRegisterUser);
		
		if (isRegister != false) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStatusLogDAO(200, "O usuario foi registrado com sucesso!") );
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o usuario"));
    }
		
	
	
	
	/**
	 * Solicita o update de um usuario
	 * 
	 * @param requestUpdateUser
	 * @return se o usuario foi atualizado ou não
	 */
	@PutMapping(value = "/user/adm/updateuser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserBackOfficeDAO> updateUser(@RequestBody UserBackOfficeDAO requestUpdateUser) {
		
		boolean isUpdated = userServiceAdmin.updateUser(requestUpdateUser);
		
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(requestUpdateUser);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(requestUpdateUser);
	}
}







