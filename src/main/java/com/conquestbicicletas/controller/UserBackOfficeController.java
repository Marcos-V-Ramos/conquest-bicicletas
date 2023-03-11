package com.conquestbicicletas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PutMapping(value = "/user/update/status", consumes = "application/json")
	public ResponseEntity<ResponseStatusLogDAO> updateStatusUser(@RequestBody UserBackOfficeDAO request){
		
		 boolean updateStatusUser = userService.updateStatusUser(request);
		
		if(updateStatusUser == true) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStatusLogDAO(200, "O usuario foi modificado para o status:" + request.getStatus()));
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatusLogDAO(500, "Não foi possivel modificar o status do usuario para: " + request.getStatus()));
	}
}
