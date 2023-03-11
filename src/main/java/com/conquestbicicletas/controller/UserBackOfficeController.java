package com.conquestbicicletas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.service.UserBackOfficeService;


@RestController
@RequestMapping("/conquest")
public class UserBackOfficeController {
	
	@Autowired
	private UserBackOfficeService userService;
	 
	@GetMapping(value = "/user/listusers", produces = "application/json")
    public ResponseEntity<List<UserBackOfficeDAO>> getListUsers() {
    	
		List<UserBackOfficeDAO> response = userService.getListUsers();
    	
    	if (response != null) {
    		return ResponseEntity.ok(response);
    	}
    	
    	return ResponseEntity.badRequest().body(null);
    }
	 
	@PostMapping(value = "/user/registeruser", consumes = "application/json", produces = "application/json")
	public boolean registerUser(@RequestBody UserBackOfficeDAO requestUser){
		boolean response =  userService.registerUser(requestUser);
		
		if (response != true) {
			return true;
		}
		
		return false;
	}
}
