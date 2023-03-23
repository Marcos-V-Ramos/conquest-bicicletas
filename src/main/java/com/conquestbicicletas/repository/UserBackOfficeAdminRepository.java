package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class UserBackOfficeAdminRepository extends ConnectionFactory {

	/**
	 * 
	 * @return listUsers
	 */
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

	
	/**
	 * 
	 * Retorna a lista de usuarios tendo o nome ou CPF como parâmetro
	 * 
	 * @param requestUserSearch
	 * @return listUsers
	 */
	public List<UserBackOfficeDAO> getListUsersSearch(UserBackOfficeDAO requestUserSearch) {
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

			} else if (requestUserSearch.getNameUser() == null && requestUserSearch.getCpf() != null) {
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
	
	
	/**
	 * 
	 * retorna a lista de usuários filtrando por grupo
	 * 
	 * @param requestTypeGroupUser
	 * @return listUsers
	 */
	public List<UserBackOfficeDAO> filterGroupUser(UserBackOfficeDAO requestTypeGroupUser){		
		List<UserBackOfficeDAO> listUsers = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_user WHERE group_user = ?");
			stmt.setInt(1, requestTypeGroupUser.getGroup());
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
	
	/**
	 * Registra o usuario
	 * 
	 * @param request
	 * @return retorna TRUE para usuario registrado, e false para usuario NÃO registrado
	 */
	public boolean registerUser(UserBackOfficeDAO request) {
		try {
			Connection connection = super.getConnection();
			// Checa se o email ou CPF já existem no banco
			boolean created = isRegistered(request, connection);
			// Se não existirem, insira os dados no banco
			if (created != true) {
				PreparedStatement stmt = connection.prepareStatement(
						"INSERT INTO tb_user (name_user, cpf_user, email_user, password_user, status_user, group_user) VALUES (?, ?, ?, AES_ENCRYPT(?, 'chave'), TRUE, ?)");
				stmt.setString(1, request.getNameUser());
				stmt.setString(2, request.getCpf());
				stmt.setString(3, request.getEmail());
				stmt.setString(4, request.getPassword());
				stmt.setInt(5, request.getGroup());

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
	
	
	/// Atualizar User
	public boolean updateUser(UserBackOfficeDAO request) {
		boolean result = false;

		final String SQL_QUERY = "UPDATE tb_user SET name_user = ?, password_user = AES_ENCRYPT(?, 'chave'), status_user = ?, group_user = ? where cpf_user = ?";
		try {
			Connection connection = super.getConnection();
			PreparedStatement updateUser = connection.prepareStatement(SQL_QUERY);
			updateUser.setString(1, request.getNameUser());
			updateUser.setString(2, request.getPassword());
			updateUser.setBoolean(3, request.getStatus());
			updateUser.setInt(4, request.getGroup());
			updateUser.setString(5, request.getCpf());

			int rows = updateUser.executeUpdate();

			result = rows > 0 ? true : false;

		} catch (SQLException e) {
			System.out.format("Houve um erro ao atualizar o usuário: %s /n %s /n %s", e.getMessage(), e.getSQLState(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}

		return result;
	}

	
	/**
	 * Checa se o Email e CPF já estão no banco de dados
	 * 
	 * 
	 * @param request
	 * @param connection
	 * @return TRUE se os dados já estão no banco, e FALSE caso não estejam
	 */
	public static boolean isRegistered(UserBackOfficeDAO request, Connection connection) {
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
