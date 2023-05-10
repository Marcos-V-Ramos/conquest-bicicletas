package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.CustomerCartDAO;
import com.conquestbicicletas.model.dao.ImageProductModelDAO;
import com.conquestbicicletas.model.dao.ProductCartDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerCartRepository extends ConnectionFactory {

	private CustomerCartDAO customerCart;

	public CustomerCartDAO getItemsCart(int customerId) {
		List<ProductCartDAO> itemCart = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			final StringBuilder stb = new StringBuilder();
			stb.append("SELECT ");
			stb.append("C.id_cart IDCART, ");
			stb.append("C.fk_id_customer IDCUSTOMER, ");
			stb.append("C.amount TOTAL, ");
			stb.append("C.status STATUSCART, ");
			stb.append("IMG.img_id IDIMAGEM, ");
			stb.append("IMG.fk_product_id IDPRODUCTIMG, ");
			stb.append("IMG.img_base64 FILEIMG, ");
			stb.append("P.product_id IDPRODUCT, ");
			stb.append("P.product_name NAMEPRODUCT, ");
			stb.append("P.product_value VALUEPRODUCT, ");
			stb.append("P.product_review REVIEWPRODUCT, ");
			stb.append("IC.id_item_cart IDITEMCART, ");
			stb.append("IC.qtd QUANTITYPRODUCT ");
			stb.append("FROM tb_cart C ");
			stb.append("LEFT JOIN tb_items_cart IC ON IC.fk_id_cart = C.id_cart " );
			stb.append("LEFT JOIN tb_product P ON P.product_id = IC.fk_id_product ");
			stb.append("LEFT JOIN tb_img_product IMG ON IMG.fk_product_id = IC.fk_id_product ");
			stb.append("WHERE fk_id_customer = ?");
	

			PreparedStatement pstmt = connection.prepareStatement(stb.toString());
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			
			customerCart = new CustomerCartDAO();
			
			while (rs.next()) {						
				customerCart.setIdCart(rs.getInt("IDCART"));
				customerCart.setUserId(rs.getInt("IDCUSTOMER"));
				customerCart.setAmountCart(rs.getDouble("TOTAL"));	
				customerCart.setStatus(rs.getString("STATUSCART").charAt(0));
				
				ProductCartDAO item = new ProductCartDAO();
				ImageProductModelDAO image = new ImageProductModelDAO();
				image.setIdImage(rs.getInt("IDIMAGEM"));
				image.setProductId(rs.getInt("IDPRODUCTIMG"));
				image.setImageBase64(rs.getString("FILEIMG"));
				item.setProductId(rs.getInt("IDPRODUCT"));
				item.setProductName(rs.getString("NAMEPRODUCT"));
				item.setProductValue(rs.getDouble("VALUEPRODUCT"));
				item.setProductReview(rs.getDouble("REVIEWPRODUCT"));
				item.setProductImage(image);
				item.setIdItemCart(rs.getInt("IDITEMCART"));
				item.setProductQtd(rs.getInt("QUANTITYPRODUCT"));
				
				itemCart.add(item);
			}
			customerCart.setProductCart(itemCart);
			return customerCart;
		} catch (SQLException e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		}

		return null;
	}

	public boolean addProductCart(int productId, int customerId, int qtd) {
		boolean response = false;
		final String SQL_QUERY;
		Integer verifyCart = verifyCart(customerId);
		try {
			Connection connection = super.getConnection();
			if (verifyCart != 0 && verifyCart != null) {
				SQL_QUERY = "INSERT INTO tb_items_cart (fk_id_product, fk_id_cart, qtd) VALUES (?, ?, ?)";
				PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
				pstmt.setInt(1, productId);
				pstmt.setInt(2, verifyCart);
				pstmt.setInt(3, qtd);

				int rows = pstmt.executeUpdate();

				return rows > 0 ? true : false;

			} else if (verifyCart == 0 && verifyCart != null) {
				SQL_QUERY = "INSERT INTO tb_items_cart (fk_id_product, fk_id_cart, qtd) VALUES (?, ?, ?)";
				Integer idCart = addCart(customerId);
				if (idCart != null) {
					PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
					pstmt.setInt(1, productId);
					pstmt.setInt(2, idCart);
					pstmt.setInt(3, qtd);

					int rows = pstmt.executeUpdate();

					return rows > 0 ? true : false;
				}
			}
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		log.error("[ERROR] Error adding product to cart");
		return response;
	}

	public Integer verifyCart(int customerId) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "SELECT id_cart FROM tb_cart WHERE fk_id_customer = ? AND status = 'O'";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			Integer idCart = 0;

			while (rs.next()) {
				idCart = rs.getInt("id_cart");
				return idCart;
			}

			return idCart;
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}

	public Integer addCart(int customerId) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "INSERT INTO tb_cart (fk_id_customer, amount, status) VALUES (?, 0.0, 'O')";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, customerId);

			int rows = pstmt.executeUpdate();

			if (rows > 0) {
				ResultSet cart = pstmt.getGeneratedKeys();
				if (cart.next()) {
					Integer idCart = cart.getInt(1);
					return idCart;
				}
				cart.close();
			}
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}

	public boolean updateQtdProductCart(int idCart, int productId, int qtd) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "UPDATE tb_items_cart SET qtd = ? WHERE fk_id_product = ? AND fk_id_cart = ?";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, qtd);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, idCart);
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

	public boolean removeProductCart(int productId, int idCart) {
		try {
			Connection connection = super.getConnection();
			final String SQL_QUERY = "DELETE FROM tb_items_cart WHERE fk_id_product = ? AND fk_id_cart = ?";
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, idCart);
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
