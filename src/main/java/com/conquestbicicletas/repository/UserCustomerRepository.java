package com.conquestbicicletas.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.UserCustomerAdressDAO;
import com.conquestbicicletas.model.dao.UserCustomerDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserCustomerRepository extends ConnectionFactory {

	/**
	 * Registra o cliente
	 * 
	 * @param request
	 * @return retorna TRUE para usuario registrado, e false para usuario NÃO
	 *         registrado
	 */
	public Integer registerCustomer(UserCustomerDAO request) {
		Integer customerId = null;
		try {
			Connection connection = super.getConnection();
			// Checa se o email ou CPF já existem no banco
			boolean created = isRegistered(request, connection);
			// Se não existirem, insira os dados no banco
			final String queryRegisterCustomer = "INSERT INTO tb_customer (name_user, cpf_user, email_user, password_user, gender_user, birthdate_user) VALUES (?, ?, ?, AES_ENCRYPT(?, 'chave'), ?, ?)";
			if (created != true) {
				PreparedStatement stmt = connection.prepareStatement(queryRegisterCustomer, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, request.getUserName());
				stmt.setString(2, request.getUserCpf());
				stmt.setString(3, request.getUserEmail());
				stmt.setString(4, request.getUserPassword());
				stmt.setString(5, request.getUserGender());
				stmt.setString(6, request.getUserBirthDate());

				int rows = stmt.executeUpdate();

				if (rows > 0) {
					ResultSet generatedId = stmt.getGeneratedKeys();
					if (generatedId.next()) {
						customerId = generatedId.getInt(1);
						return customerId;
					}
					generatedId.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			super.closeConnection();
		}
		System.out.println("Esse Cliente já existe!");
		return customerId;
	}

	/**
	 * Registra o endereço do cliente
	 * 
	 * @param
	 * @return
	 */
	public boolean registerAdress(UserCustomerAdressDAO requestRegisterAdress) {
		try {
			Connection connection = super.getConnection();

			final String queryRegisterAdress = "INSERT INTO tb_adress (cep, logradouro, bairro, localidade, uf, complemento,numero,is_adress_customer, fk_id_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(queryRegisterAdress);

			stmt.setString(1, requestRegisterAdress.getCep());
			stmt.setString(2, requestRegisterAdress.getLogradouro());
			stmt.setString(3, requestRegisterAdress.getBairro());
			stmt.setString(4, requestRegisterAdress.getLocalidade());
			stmt.setString(5, requestRegisterAdress.getUf());
			stmt.setString(6, requestRegisterAdress.getComplemento());
			stmt.setString(7, requestRegisterAdress.getNumero());
			stmt.setBoolean(8, requestRegisterAdress.getAdressCustomer());
			stmt.setInt(9, requestRegisterAdress.getUserId());

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

	/*
	 * Executa o update do cliente
	 */
	public boolean updateCustomer(UserCustomerDAO requestUpdateCustomer) {
		boolean response = false;

		final String SQL_QUERY = "UPDATE tb_customer SET name_user = ?, birthdate_user = ?, gender_user = ?,  password_user = AES_ENCRYPT(?, 'chave') WHERE fk_id_user = ?";
		try {
			Connection connection = super.getConnection();
			PreparedStatement updateCustomer = connection.prepareStatement(SQL_QUERY);
			updateCustomer.setString(1, requestUpdateCustomer.getUserName());
			updateCustomer.setString(2, requestUpdateCustomer.getUserBirthDate());
			updateCustomer.setString(3, requestUpdateCustomer.getUserGender());
			updateCustomer.setString(4, requestUpdateCustomer.getUserPassword());

			int rows = updateCustomer.executeUpdate();

			return response = rows > 0 ? true : false;

		} catch (SQLException e) {
			log.info("There was an error connecting to the database: %s /n %s /n %s", e.getMessage(), e.getSQLState(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		log.info("[ERROR] There was an error changing customer informations");
		return response;
	}

	/*
	 * Inserir um novo endereco
	 */
	public boolean registerAdress(UserCustomerAdressDAO requestRegisterAdress) {
		boolean response = false;
		try {
			final String SQL_QUERY = "INSERT INTO tb_adress (cep, logradouro, bairro, localidade, uf, complemento, numero, status, is_adress_customer, fk_id_user) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, TRUE, ?, ?)";

			Connection connection = super.getConnection();
			PreparedStatement insertAdress = connection.prepareStatement(SQL_QUERY);
			insertAdress.setString(1, requestRegisterAdress.getCep());
			insertAdress.setString(2, requestRegisterAdress.getLogradouro());
			insertAdress.setString(3, requestRegisterAdress.getBairro());
			insertAdress.setString(4, requestRegisterAdress.getLocalidade());
			insertAdress.setString(5, requestRegisterAdress.getUf());
			insertAdress.setString(6, requestRegisterAdress.getComplemento());
			insertAdress.setInt(7, requestRegisterAdress.getNumero());
			insertAdress.setBoolean(8, requestRegisterAdress.isStatus());
			insertAdress.setBoolean(9, requestRegisterAdress.isAdressCustomer());
			insertAdress.setInt(10, requestRegisterAdress.getUserId());

			int rows = insertAdress.executeUpdate();

			return response = rows > 0 ? true : false;

		} catch (SQLException e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(), e.getSQLState(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		log.info("[ERROR] There was an error registering a new address");
		return response;
	}

	/**
	 * 
	 * retorna a lista de usuários filtrando por grupo
	 * 
	 * @param requestTypeGroupUser
	 * @return listUsers
	 */
	public List<UserCustomerAdressDAO> getAllAdressCustomer(int userId) {
		List<UserCustomerAdressDAO> listAdress = new ArrayList<>();
		try {
			Connection connection = super.getConnection();

			final String SQL_QUERY = "SELECT id_adress, cep, logradouro, bairro, localidade, "
					+ "uf, complemento, numero, status, is_adress_customer " + "FROM tb_adress WHERE fk_id_user = ? ";

			PreparedStatement stmt = connection.prepareStatement(SQL_QUERY);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserCustomerAdressDAO adress = new UserCustomerAdressDAO();
				adress.setAdressId(rs.getInt("id_adress"));
				adress.setCep(rs.getString("cep"));
				adress.setLogradouro(rs.getString("logradouro"));
				adress.setBairro(rs.getString("bairro"));
				adress.setLocalidade(rs.getString("localidade"));
				adress.setUf(rs.getString("uf"));
				adress.setComplemento(rs.getString("complemento"));
				adress.setNumero(rs.getInt("numero"));
				adress.setStatus(rs.getBoolean("status"));
				adress.setAdressCustomer(rs.getBoolean("is_adress_customer"));
				listAdress.add(adress);

			}
			return listAdress;
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage());
		} finally {
			super.closeConnection();
		}
		log.info("[ERROR] Unable to list addresses");
		return null;
	}

	/*
	 * Desabilita endereco
	 * 
	 */
	public boolean disableAdress(int adressId) {
		boolean response = false;
		try {
			Connection connection = super.getConnection();

			final String SQL_QUERY = "UPDATE tb_adress SET status = FALSE WHERE id_adress = ?";

			PreparedStatement disableAdress = connection.prepareStatement(SQL_QUERY);
			disableAdress.setInt(1, adressId);
			
			int rows = disableAdress.executeUpdate();
			
			return response = rows > 0 ? true : false;
			
		} catch (SQLException e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage());
		}
		log.info("[ERROR] Unable to disable address");
		return response;	
	}
  
  	/**
	 * Verifica se o Email e CPF já estão cadastrados no banco de dados
	 * 
	 * 
	 * @param request
	 * @param connection
	 * @return TRUE se os dados já estão no banco, e FALSE caso não estejam
	 */
	public static boolean isRegistered(UserCustomerDAO request, Connection connection) {
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * from tb_customer WHERE cpf_user = ? OR email_user = ?");
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
