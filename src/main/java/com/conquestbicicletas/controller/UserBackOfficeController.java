package com.conquestbicicletas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.ResponseStatusLogDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.service.UserBackOfficeService;


@RestController
@RequestMapping("/conquest")
public class UserBackOfficeController {
	
	@Autowired
	private UserBackOfficeService userService;
	 
	/**
	 * Registra usuario
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/user/registeruser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> registerUser(@RequestBody UserBackOfficeDAO requestRegisterUser){
		boolean response =  userService.registerUser(requestRegisterUser);
		
		if (response != false) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStatusLogDAO(200, "O usuario foi registrado com sucesso!") );
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseStatusLogDAO(500, "Não foi possivel registrar o usuario"));
    }
	
	
	/**
	 * 
	 * 
	 * @param requestUpdateStatus
	 * @return
	 */
	@PutMapping(value = "/user/update/status", consumes = "application/json")
	public ResponseEntity<Boolean> updateStatusUser(@RequestBody UserBackOfficeDAO requestUpdateStatus){
		
		boolean updateStatusUser = userService.updateStatusUser(requestUpdateStatus);
		
		if(updateStatusUser == true) {
			return ResponseEntity.status(HttpStatus.OK).body(requestUpdateStatus.getStatus());
		}
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	} 
		
	
	@PutMapping(value = "/user/update/updateuser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserBackOfficeDAO> updateUser(@RequestBody UserBackOfficeDAO request) {
		
		boolean isUpdated = userService.updateUser(request);
		
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(request);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);
	}
}
