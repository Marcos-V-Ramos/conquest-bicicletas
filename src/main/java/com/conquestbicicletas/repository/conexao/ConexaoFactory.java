package com.conquestbicicletas.repository.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConexaoFactory {

	// Puxa os dados da "application.properties" e transforma em Strings
	@Value("${conquestbicicletas.database.driver}")
	private String driver;

	@Value("${conquestbicicletas.database.url}")
	private String url;

	@Value("${conquestbicicletas.database.username}")
	private String username;

	@Value("${conquestbicicletas.database.password}")
	private String password;

	private static Connection conexao = null;

	// tenta a conexão com o banco se utilizando das Strings
	protected Connection getConnection() {
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, username, password);
			conexao.beginRequest();
			return conexao;
		} catch (Exception e) {
			// Melhorar essa Exception posteriormente
			System.out.println("Erro ao conectar com o banco");
			return null;
		}

	}

	protected void closeConnection() {
		try {
			conexao.endRequest();
			conexao.close();
		} catch (Exception e) {
			System.out.println("Erro ao fechar a conexão!");
		}
	}

}
