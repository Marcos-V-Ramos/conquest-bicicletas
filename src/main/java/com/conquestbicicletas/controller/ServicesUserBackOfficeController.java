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
import com.conquestbicicletas.service.ServicesUserBackOfficeService;


@RestController
@RequestMapping("/conquest")
public class ServicesUserBackOfficeController {

	@Autowired
	private ServicesUserBackOfficeService userService;
	 
	@GetMapping(value = "/user/listusers", produces = "application/json")
    public ResponseEntity<List<UserBackOfficeDAO>> getListUsers() {
    	
		List<UserBackOfficeDAO> response = userService.getListUsers();
    	
    	if (response != null) {
    		return ResponseEntity.ok(response);
    	}
    	
    	return ResponseEntity.badRequest().body(null);
    }
	
	
	@PostMapping(value = "/user/listusers/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<UserBackOfficeDAO>> getListUsersSearch(@RequestBody UserBackOfficeDAO request){
		
		List<UserBackOfficeDAO> response = userService.getListUsers(request);
		
		if(response != null) {
			return ResponseEntity.ok(response);
		}
		
		return ResponseEntity.badRequest().body(null);
	}
}
