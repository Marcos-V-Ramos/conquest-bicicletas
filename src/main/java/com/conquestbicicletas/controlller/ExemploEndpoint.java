package com.conquestbicicletas.controlller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class ExemploEndpoint {
	
	@GetMapping("/teste")
	public String texto() {
		System.out.println("teste se entrou");
		return "Testando API";
		
	}
}
