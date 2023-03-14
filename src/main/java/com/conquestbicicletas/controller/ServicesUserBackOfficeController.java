package com.conquestbicicletas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.service.ServicesUserBackOfficeService;


@RestController
@RequestMapping("/conquest")
public class ServicesUserBackOfficeController {

	@Autowired
	private ServicesUserBackOfficeService userService;
	 
	/**
	 * Lista todos os usuarios cadastrados no backOffice
	 * 
	 * @return Lista de usuarios
	 */
	@GetMapping(value = "/user/listusers", produces = "application/json")
    public ResponseEntity<List<UserBackOfficeDAO>> getListAllUsers() {
    	
		List<UserBackOfficeDAO> response = userService.getListUsers();
    	
    	if (response != null) {
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
	
	
	/**
	 * Pesquisa no banco de dados um determinado usuario por nome, cpf ou grupo
	 * 
	 * @param requestUserSearch
	 * @return
	 */
	@PostMapping(value = "/user/listusers/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> getListSearchUsers(@RequestBody UserBackOfficeDAO requestUserSearch){
		
		List<UserBackOfficeDAO> response = userService.getListUsers(requestUserSearch);
		
		if(response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	
	/**
	 * 
	 * @param requestTypeGroupUser; tipo de grupo
	 * @return retorna uma lista com o filtro selecionado.
	 */
	@PostMapping(value = "/user/listusers/filtergroup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> filterGroupUser(@RequestBody UserBackOfficeDAO requestTypeGroupUser){
		
		List<UserBackOfficeDAO> response = userService.filterGroupUser(requestTypeGroupUser);
		
		if(response != null) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
