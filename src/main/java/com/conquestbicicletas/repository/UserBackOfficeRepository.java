package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.UpdateStatusUserDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserBackOfficeRepository extends ConnectionFactory {

	public UserBackOfficeDAO getUser(int userId) {
		UserBackOfficeDAO userBackOffice = null;
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "SELECT id_user, name_user, cpf_user, email_user, "
					+ "AES_DECRYPT(password_user, 'chave') AS password_user,"
					+ " group_user, status_user FROM tb_user WHERE id_user = ?";

			PreparedStatement psmt = connection.prepareStatement(SQL_QUERY);
			psmt.setInt(1, userId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				userBackOffice = new UserBackOfficeDAO(rs.getInt("id_user"), rs.getString("name_user"),
						rs.getString("cpf_user"), rs.getString("email_user"), rs.getString("password_user"),
						rs.getInt("group_user"), rs.getBoolean("status_user"));
			}
			return userBackOffice;
		} catch (SQLException e) {
			log.error("There was an error connecting to the database: %s /n %s /n %s", e.getMessage(), e.getSQLState(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return userBackOffice;
	}

	/**
	 * 
	 * @return listUsers
	 */
	public List<UserBackOfficeDAO> getListUser() {
		List<UserBackOfficeDAO> listUsers = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "SELECT id_user, name_user, cpf_user, email_user, "
					+ "group_user, status_user FROM tb_user";

			PreparedStatement stmt = connection.prepareStatement(SQL_QUERY);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserBackOfficeDAO userBackOffice = new UserBackOfficeDAO();
				userBackOffice.setUserId(rs.getInt("id_user"));
				userBackOffice.setUserName(rs.getString("name_user"));
				userBackOffice.setUserCpf(rs.getString("cpf_user"));
				userBackOffice.setUserEmail(rs.getString("email_user"));
				userBackOffice.setUserGroup(rs.getInt("group_user"));
				userBackOffice.setUserStatus(rs.getBoolean("status_user"));
				listUsers.add(userBackOffice);
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
	public List<UserBackOfficeDAO> getListUserSearch(UserBackOfficeDAO requestUserSearch) {
		List<UserBackOfficeDAO> listUsers = new ArrayList<>();
		try {
			Connection connection = super.getConnection();

			if (requestUserSearch.getUserName() != null && requestUserSearch.getUserCpf() == null) {

				final String SQL_QUERY = "SELECT id_user, name_user, cpf_user, email_user, "
						+ "status_user, group_user FROM tb_user WHERE name_user " + "COLLATE utf8mb4_general_ci LIKE ?";

				PreparedStatement stmt = connection.prepareStatement(SQL_QUERY);
				stmt.setString(1, "%" + requestUserSearch.getUserName() + "%");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					UserBackOfficeDAO userBackOffice = new UserBackOfficeDAO();
					userBackOffice.setUserId(rs.getInt("id_user"));
					userBackOffice.setUserName(rs.getString("name_user"));
					userBackOffice.setUserCpf(rs.getString("cpf_user"));
					userBackOffice.setUserEmail(rs.getString("email_user"));
					userBackOffice.setUserStatus(rs.getBoolean("status_user"));
					userBackOffice.setUserGroup(rs.getInt("group_user"));
					listUsers.add(userBackOffice);
				}
				return listUsers;

			} else if (requestUserSearch.getUserName() == null && requestUserSearch.getUserCpf() != null) {

				final String SQL_QUERY = "SELECT id_user, name_user, cpf_user, email_user, "
						+ "status_user, group_user FROM tb_user WHERE cpf_user " + "COLLATE utf8mb4_general_ci LIKE ?";

				PreparedStatement stmt = connection.prepareStatement(SQL_QUERY);
				stmt.setString(1, "%" + requestUserSearch.getUserCpf() + "%");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					UserBackOfficeDAO userBackOffice = new UserBackOfficeDAO();
					userBackOffice.setUserId(rs.getInt("id_user"));
					userBackOffice.setUserName(rs.getString("name_user"));
					userBackOffice.setUserCpf(rs.getString("cpf_user"));
					userBackOffice.setUserEmail(rs.getString("email_user"));
					userBackOffice.setUserStatus(rs.getBoolean("status_user"));
					userBackOffice.setUserGroup(rs.getInt("group_user"));
					listUsers.add(userBackOffice);
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
	public List<UserBackOfficeDAO> filterGroupUser(int requestTypeGroupUser) {
		List<UserBackOfficeDAO> listUsers = new ArrayList<>();
		try {
			Connection connection = super.getConnection();

			final String SQL_QUERY = "SELECT id_user, name_user, cpf_user, email_user, "
					+ "status_user, group_user FROM tb_user WHERE group_user = ? ";

			PreparedStatement stmt = connection.prepareStatement(SQL_QUERY);
			stmt.setInt(1, requestTypeGroupUser);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserBackOfficeDAO userBackOffice = new UserBackOfficeDAO();
				userBackOffice.setUserId(rs.getInt("id_user"));
				userBackOffice.setUserName(rs.getString("name_user"));
				userBackOffice.setUserCpf(rs.getString("cpf_user"));
				userBackOffice.setUserEmail(rs.getString("email_user"));
				userBackOffice.setUserStatus(rs.getBoolean("status_user"));
				userBackOffice.setUserGroup(rs.getInt("group_user"));
				listUsers.add(userBackOffice);
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
	 * @return retorna TRUE para usuario registrado, e false para usuario NÃO
	 *         registrado
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
				stmt.setString(1, request.getUserName());
				stmt.setString(2, request.getUserCpf());
				stmt.setString(3, request.getUserEmail());
				stmt.setString(4, request.getUserPassword());
				stmt.setInt(5, request.getUserGroup());

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

		final String SQL_QUERY = "UPDATE tb_user SET name_user = ?, cpf_user = ?, password_user = AES_ENCRYPT(?, 'chave'), group_user = ? WHERE id_user = ?";
		try {
			Connection connection = super.getConnection();
			PreparedStatement updateUser = connection.prepareStatement(SQL_QUERY);
			updateUser.setString(1, request.getUserName());
			updateUser.setString(2, request.getUserCpf());
			updateUser.setString(3, request.getUserPassword());
			updateUser.setInt(4, request.getUserGroup());
			updateUser.setInt(5, request.getUserId());

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
	 * 
	 * @param requestUpdateStatus
	 * @return
	 */
	public boolean updateStatus(UpdateStatusUserDAO requestUpdateStatus) {

		try {
			Connection connection = super.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE tb_user ");
			sb.append(" SET status_user = ?");
			sb.append(" WHERE id_user = ?");

			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			stmt.setBoolean(1, requestUpdateStatus.getUserStatus());
			stmt.setInt(2, requestUpdateStatus.getUserId());

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
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
			stmt.setString(1, request.getUserCpf());
			stmt.setString(2, request.getUserEmail());

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
