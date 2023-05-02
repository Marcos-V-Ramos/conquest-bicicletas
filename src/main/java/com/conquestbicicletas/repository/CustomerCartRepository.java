package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Repository;

import com.conquestbicicletas.repository.config.ConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerCartRepository extends ConnectionFactory {

	public boolean addProductCart(int productId, int customerId, int qtd) {
		boolean response = false;

		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "INSERT INTO tb_cart (fk_id_product, fk_id_customer, qtd) VALUES (?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(SQL_QUERY);
			stmt.setInt(1, productId);
			stmt.setInt(2, customerId);
			stmt.setInt(3, qtd);
			int rows = stmt.executeUpdate();

			return response = rows > 0 ? true : false;
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return response;
	}
}
