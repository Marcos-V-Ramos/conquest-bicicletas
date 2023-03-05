package com.conquestbicicletas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	private String nomeUsuario;
	private String email;
	private Boolean status;
	private String senha;
	private int grupo;
	private String cpf;

}
