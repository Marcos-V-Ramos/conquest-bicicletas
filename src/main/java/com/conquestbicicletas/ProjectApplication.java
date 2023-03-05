package com.conquestbicicletas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.conquestbicicletas.model.Usuario;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		
		Usuario teste = new Usuario();
		System.out.println("teste" + teste);
		
	}

}
