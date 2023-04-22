package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.UserCustomerAdressDAO;
import com.conquestbicicletas.model.dao.UserCustomerDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class UserCustomerRepository extends ConnectionFactory {
	
	
	
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
	
	
	/**
	 * Registra o  cliente
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
			if (created != true) {
				PreparedStatement stmt = connection.prepareStatement(
						"INSERT INTO tb_customer (name_user, cpf_user, email_user, password_user, gender_user, birthdate_user) VALUES (?, ?, ?, AES_ENCRYPT(?, 'chave'), ?, ?)");
				stmt.setString(1, request.getUserName());
				stmt.setString(2, request.getUserCpf());
				stmt.setString(3, request.getUserEmail());
				stmt.setString(4, request.getUserPassword());
				stmt.setString(5, request.getUserGender());
				stmt.setString(6, request.getUserBirthDate());
				stmt.setArray(0, null);
				

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
	 * @param 
	 * @return
	 */
	public boolean registerAdress(UserCustomerAdressDAO requestRegisterAdress) {
		try {
			Connection connection = super.getConnection();

			final String queryRegisterAdress = 
					"INSERT INTO tb_adress (cep, logradouro, bairro, localidade, uf, complemento,numero,is_adress_customer, fk_id_user) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(queryRegisterAdress);

			stmt.setInt(1, requestRegisterAdress.getCep());
			stmt.setString(2, requestRegisterAdress.getLogradouro());
			stmt.setString(3, requestRegisterAdress.getBairro());
			stmt.setString(4, requestRegisterAdress.getLocalidade());
			stmt.setString(5, requestRegisterAdress.getUf());
			stmt.setString(6, requestRegisterAdress.getComplemento());
			stmt.setString(7, requestRegisterAdress.getNumero());
			stmt.setBoolean(8, requestRegisterAdress.getAdressCustomer());
			stmt.setString(9, requestRegisterAdress.getBairro());
			
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
	

}
