package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.AuthenticateUserCustomerResponseDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserRequestDAO;
import com.conquestbicicletas.model.dao.AuthenticateUserBackOfficeResponseDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class AuthenticateUserRepository extends ConnectionFactory {

	/**
	 * Verificação de Status ativo. Se status == true, retorna o grupo do usuario se
	 * status for status == false, retorna 0 Autenticação de cliente backoffice ao
	 * fazer login
	 *
	 * @param requestLoginUser
	 * @return grupo em que o usuario logado pertence
	 */
	public AuthenticateUserBackOfficeResponseDAO authenticateUserBackOffice(AuthenticateUserRequestDAO requestLoginUser) {
		Connection conexao = super.getConnection();

		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"SELECT * FROM tb_user WHERE email_user = ? AND AES_DECRYPT(password_user, 'chave') = ?");
			stmt.setString(1, requestLoginUser.getUserEmail());
			stmt.setString(2, requestLoginUser.getUserPassword());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				boolean status = rs.getBoolean("status_user");
				if (status == true) {
					return new AuthenticateUserBackOfficeResponseDAO(rs.getInt("id_user"), rs.getInt("group_user"));
				}

				return new AuthenticateUserBackOfficeResponseDAO(0, 0);
			}

		} catch (Exception e) {
			System.out.println("Erro ao autenticar usuario!" + e.getMessage() + e.getCause());
			return null;
		} finally {
			super.closeConnection();
		}

		return null;
	}

	/// ALTERAR ROTAS
	/***
	 * Autenticação de cliente ao fazer login
	 * 
	 * @param requestLoginCustomer
	 * @return
	 */
	public AuthenticateUserCustomerResponseDAO authenticateCustomer(AuthenticateUserRequestDAO requestLoginCustomer) {
		Connection connection = super.getConnection();

		try {
			PreparedStatement stmt = connection.prepareStatement(
					"SELECT * FROM tb_customer WHERE email_user = ? AND AES_DECRYPT(password_user, 'chave') = ?");
			stmt.setString(1, requestLoginCustomer.getUserEmail());
			stmt.setString(2, requestLoginCustomer.getUserPassword());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				boolean status = rs.getBoolean("status_user");
				if (status == true) {
					return new AuthenticateUserCustomerResponseDAO(rs.getInt("id_user"));
				}

				return new AuthenticateUserCustomerResponseDAO(0);
			}

		} catch (Exception e) {
			System.out.println("Erro ao autenticar usuario!" + e.getMessage() + e.getCause());
			return null;
		} finally {
			super.closeConnection();
		}

		return null;
	}

}
