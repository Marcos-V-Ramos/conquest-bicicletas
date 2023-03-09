package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class AutenticateUserBackOfficeRepository extends ConnectionFactory {
	public AuthenticateUserBackOfficeResponseDAO authenticateUserBackOffice(AuthenticateUserBackOfficeRequestDAO request) {
		Connection conexao = super.getConnection();
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * FROM usuario WHERE email = ? and senha = ? ");
			stmt.setString(1, request.getEmail());
			stmt.setString(2, request.getPassword());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return  new AuthenticateUserBackOfficeResponseDAO(rs.getInt("group"));
			}

		} catch (Exception e) {
			System.out.println("Erro ao autenticar usuario!" + e.getMessage() + e.getCause());
			return null;
		}
	 
		return null;
	}
}
