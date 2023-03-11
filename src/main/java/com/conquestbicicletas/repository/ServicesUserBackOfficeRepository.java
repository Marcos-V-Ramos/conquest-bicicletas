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
public class ServicesUserBackOfficeRepository extends ConnectionFactory {

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
		} finally {
			super.closeConnection();
		}
		return null;
	}

	
	
	public List<UserBackOfficeDAO> getListUsers(UserBackOfficeDAO requestUserSearch) {
		List<UserBackOfficeDAO> listUsers = new ArrayList<>();
		try {

			Connection connection = super.getConnection();

			if (requestUserSearch.getNameUser() != null && requestUserSearch.getCpf() == null) {
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_user WHERE name_user = ?");
				stmt.setString(1, requestUserSearch.getNameUser());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					listUsers.add(new UserBackOfficeDAO(rs.getString("name_user"), rs.getString("cpf_user"),
							rs.getString("email_user"), rs.getString("password_user"), rs.getBoolean("status_user"),
							rs.getInt("group_user")));
				}
				return listUsers;

			} else if (requestUserSearch.getNameUser() != null && requestUserSearch.getCpf() == null) {
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_user WHERE cpf_user = ?");
				stmt.setString(1, requestUserSearch.getCpf());
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					listUsers.add(new UserBackOfficeDAO(rs.getString("name_user"), rs.getString("cpf_user"),
							rs.getString("email_user"), rs.getString("password_user"), rs.getBoolean("status_user"),
							rs.getInt("group_user")));
				}
				return listUsers;
			}

		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}
}
