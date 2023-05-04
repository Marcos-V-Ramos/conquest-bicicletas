package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.InfoCartAuxDAO;
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
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, customerId);
			pstmt.setInt(3, qtd);
			int rows = pstmt.executeUpdate();

			return response = rows > 0 ? true : false;
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return response;
	}

	public Integer verifyQtdProductCart(int productId, int customerId) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "SELECT qtd FROM tb_cart WHERE fk_id_product = ? AND fk_id_customer = ?";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, customerId);
			ResultSet rs = pstmt.executeQuery();
			int qtd = 0;
			
			while (rs.next()) {
				qtd = rs.getInt("qtd");
			}
			
			return qtd;			
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}
	
	
	public Boolean verifyProductCart(int productId, int customerId) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "SELECT * FROM tb_cart WHERE fk_id_product = ? AND fk_id_customer = ?";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, customerId);
			ResultSet rs = pstmt.executeQuery();

			int count = 0;
			
			while (rs.next()) {
				count++;
			}
			
			if(count > 0) {
				return true;
			}
			return false;

		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}

	public boolean updateQtdProductCart(int productId, int customerId, int qtd) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "UPDATE tb_cart SET qtd = ? WHERE fk_id_product = ? AND fk_id_customer = ?";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, qtd);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, customerId);
			int rows = pstmt.executeUpdate();

			return rows > 0 ? true : false;

		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return false;
	}

	public List<InfoCartAuxDAO> getProductCartCustomer(int customerId) {
		List<InfoCartAuxDAO> productCart = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "SELECT fk_id_customer, qtd FROM tb_cart WHERE fk_id_customer = ?";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				InfoCartAuxDAO infoCart = new InfoCartAuxDAO(rs.getInt("fk_id_customer"), rs.getInt("qtd"));
				productCart.add(infoCart);
			}
			return productCart;
		} catch (SQLException e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		}

		return null;
	}

	public boolean removeProductCart(int productId, int customerId) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "DELETE FROM tb_cart WHERE fk_id_product = ? AND fk_id_customer = ?";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, customerId);
			int rows = pstmt.executeUpdate();

			return rows > 0 ? true : false;

		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return false;
	}
}
