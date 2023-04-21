package com.conquestbicicletas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conquestbicicletas.service.UserCustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/conquest")
public class UserCustomerController {
	
	@Autowired
	private UserCustomerService userCustomerService;

}
