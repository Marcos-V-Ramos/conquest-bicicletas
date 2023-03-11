package com.conquestbicicletas.repository.config;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.beans.factory.annotation.Value;

public class ConnectionFactory {

	@Value("${conquestbicicletas.database.driver}")
	private String driver;

	@Value("${conquestbicicletas.database.url}")
	private String url;

	@Value("${conquestbicicletas.database.username}")
	private String username;

	@Value("${conquestbicicletas.database.password}")
	private String password;

	private static Connection connection = null;

	// tenta a conexão com o banco se utilizando das Strings
	protected Connection getConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			connection.beginRequest();
			return connection;
		} catch (Exception e) {
			// Melhorar essa Exception posteriormente
			System.out.println("Erro ao conectar com o banco" + e.getMessage());
			return null;
		}

	}

	protected void closeConnection() {
		try {
			connection.endRequest();
			connection.close();
		} catch (Exception e) {
			System.out.println("Erro ao fechar a conexão!");
		}
	}
}
