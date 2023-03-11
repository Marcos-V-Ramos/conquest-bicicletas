package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;


import org.springframework.stereotype.Repository;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class UserBackOfficeRepository extends ConnectionFactory {
	
	public boolean updateStatusUser(UserBackOfficeDAO requestUpdateStatus) {
		try {
			Connection connection = super.getConnection();
			
			if(requestUpdateStatus.getCpf() != null) {
				PreparedStatement stmt = connection.prepareStatement("UPDATE tb_user SET status_user = ? WHERE cpf_user = ?");
				stmt.setBoolean(1, requestUpdateStatus.getStatus());
				stmt.setString(2, requestUpdateStatus.getCpf());
				int rows = stmt.executeUpdate();
				
				if (rows > 0) {
					return true;
				}
				
				return false;
			}			
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return false;
	}

	public boolean registerUser(UserBackOfficeDAO request) {
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO tb_user (name_user, cpf_user, email_user, password_user, status_user, group_user) VALUES (?, ?, ?, ?, TRUE, ?)");
			stmt.setString(1, request.getNameUser());
			stmt.setString(2, request.getCpf());
			stmt.setString(3, request.getEmail());
			stmt.setString(4, request.getPassword());
			stmt.setInt(5, request.getGroup());

			int rows = stmt.executeUpdate();

			if (rows > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			super.closeConnection();
		}
		return false;
	}
}
