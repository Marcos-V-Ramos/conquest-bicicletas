package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class UserBackOfficeRepository extends ConnectionFactory {

	public List<UserBackOfficeDAO> getListUsers() {
		List<UserBackOfficeDAO> listUsers = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_user");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listUsers.add(new UserBackOfficeDAO(rs.getString("name_user"), rs.getString("cpf_user"),
						rs.getString("email_user"), rs.getString("password_user"), rs.getBoolean("status_user"),
						rs.getInt("group_user")));
			}
			return listUsers;
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
			// TODO retornar logs
//			log.error("[ OUT - CREATE SERVICE ] Error in get jobs repository: {} ", e);
		} finally {
			super.closeConnection();
		}
		// TODO retornar logs
//		log.info("[ OUT - GET JOBS ] Finshed search jobs: {} ", listJobs.toString());
		return null;
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
