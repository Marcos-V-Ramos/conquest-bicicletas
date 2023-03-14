package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;
import com.mysql.cj.xdevapi.Result;

@Repository
public class UserBackOfficeRepository extends ConnectionFactory {

	public boolean updateStatusUser(UserBackOfficeDAO requestUpdateStatus) {
		try {
			Connection connection = super.getConnection();

			if (requestUpdateStatus.getCpf() != null) {
				PreparedStatement stmt = connection
						.prepareStatement("UPDATE tb_user SET status_user = ? WHERE cpf_user = ?");
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
	
	public boolean registerUser(UserBackOfficeDAO requestRegisterUser) {
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement(
					"INSERT INTO tb_user (name_user, cpf_user, email_user, password_user, status_user, group_user) VALUES (?, ?, ?, ?, TRUE, ?)");
			stmt.setString(1, requestRegisterUser.getNameUser());
			stmt.setString(2, requestRegisterUser.getCpf());
			stmt.setString(3, requestRegisterUser.getEmail());
			stmt.setString(4, requestRegisterUser.getPassword());
			stmt.setInt(5, requestRegisterUser.getGroup());
				int rows = stmt.executeUpdate();

				if (rows > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			super.closeConnection();
		}
		System.out.println("Esse Usuario ja existe!!!");
		return false;
	}

	/**
	 * Checa se o Email e senha já estão no banco de dados
	 * 
	 * 
	 * @param request
	 * @param connection
	 * @return TRUE se os dados já estão no banco, e FALSE caso não estejam
	 */
	public static boolean isRegister(UserBackOfficeDAO request, Connection connection) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * from tb_user WHERE cpf_user = ? OR email_user = ?");
			stmt.setString(1, request.getCpf());
			stmt.setString(2, request.getEmail());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
