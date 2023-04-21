package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.ImageProductModelDAO;
import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.model.dao.UpdateStatusProductDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class ProductRepository extends ConnectionFactory {

	/**
	 * 
	 * @return listProduct
	 */
	public List<ProductModelDAO> getAllListProduct() {
		List<ProductModelDAO> listProduct = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_product ORDER BY product_id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listProduct.add(new ProductModelDAO(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("product_description"), rs.getInt("product_quantity"),
						rs.getDouble("product_value"), rs.getDouble("product_review"),
						rs.getBoolean("product_status")));
			}
			return listProduct;
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return null;

	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	public ProductModelDAO visualizeProduct(int productId) {
		ProductModelDAO infoProduct = null;
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_product WHERE product_id = ?");
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				infoProduct = new ProductModelDAO(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("product_description"), rs.getInt("product_quantity"),
						rs.getDouble("product_value"), rs.getDouble("product_review"), rs.getBoolean("product_status"));
			}
			return infoProduct;
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	public List<ImageProductModelDAO> visualizeProductImage(int productId) {

		List<ImageProductModelDAO> imgProduct = new ArrayList<>();

		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_img_product WHERE fk_product_id = ?");
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				imgProduct.add(new ImageProductModelDAO(rs.getInt("img_id"),rs.getInt("fk_product_id"),
						rs.getString("img_base64")));
			}
			return imgProduct;
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}

	/**
	 * Registra o produto
	 * 
	 * @param request
	 * @return retorna TRUE para usuario registrado, e false para usuario NÃO
	 *         registrado
	 */
	public Integer registerProduct(ProductModelDAO requestRegisterProduct) {
		Integer productId = null;

		try {
			Connection connection = super.getConnection();

			final String queryRegisterProduct = "INSERT INTO tb_product (product_name, product_description, product_quantity, product_value, product_review, product_status) VALUES (?, ?, ?, ?, ?,TRUE)";
			PreparedStatement stmt = connection.prepareStatement(queryRegisterProduct, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, requestRegisterProduct.getProductName());
			stmt.setString(2, requestRegisterProduct.getProductDescription());
			stmt.setInt(3, requestRegisterProduct.getProductQuantity());
			stmt.setDouble(4, requestRegisterProduct.getProductValue());
			stmt.setDouble(5, requestRegisterProduct.getProductReview());

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet generatedId = stmt.getGeneratedKeys();

				if (generatedId.next()) {
					productId = generatedId.getInt(1);
					return productId;
				}

				generatedId.close();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			super.closeConnection();
		}
		return productId;
	}

	/**
	 * 
	 * @param requestRegisterImage
	 * @return
	 */
	public boolean registerImage(ImageProductModelDAO requestRegisterImage) {
		try {
			Connection connection = super.getConnection();

			final String queryRegisterImage = "INSERT INTO tb_img_product (fk_product_id, img_base64) VALUES (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(queryRegisterImage);

			stmt.setInt(1, requestRegisterImage.getProductId());
			stmt.setString(2, requestRegisterImage.getImageBase64());
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
	 * Deleta imagem do produto
	 * @param idImage
	 * @return
	 */
	public boolean deleteImage(int idImage) {
		try {
			Connection connection = super.getConnection();

			final String queryDeleteImage = "DELETE FROM tb_img_product WHERE img_id = ?";
			PreparedStatement stmt = connection.prepareStatement(queryDeleteImage);

			stmt.setInt(1, idImage);
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
	 * 
	 * @param Alterar produtos.
	 * @return
	 */
	public boolean updateProduct(ProductModelDAO request) {
		boolean result = false;

		final String SQL_QUERY = "UPDATE tb_product SET product_name = ?, product_description = ?, "
				+ "product_quantity = ? , product_value = ? , product_status = ? WHERE product_id = ?";
		try {
			Connection connection = super.getConnection();
			PreparedStatement updateProduct = connection.prepareStatement(SQL_QUERY);
			updateProduct.setString(1, request.getProductName());
			updateProduct.setString(2, request.getProductDescription());
			updateProduct.setInt(3, request.getProductQuantity());
			updateProduct.setDouble(4, request.getProductValue());
			updateProduct.setBoolean(5, request.getProductStatus());
			updateProduct.setInt(6, request.getProductId());

			int rows = updateProduct.executeUpdate();

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
	 * @param requestUpdateStatusProduct
	 * @return
	 */
	public boolean updateStatusProduct(UpdateStatusProductDAO requestUpdateStatusProduct) {

		try {
			Connection connection = super.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE tb_product ");
			sb.append(" SET product_status = ?");
			sb.append(" WHERE product_id = ?");

			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			stmt.setBoolean(1, requestUpdateStatusProduct.getProductStatus());
			stmt.setInt(2, requestUpdateStatusProduct.getProductId());

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				super.closeConnection();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * 
	 * Retorna a pesquisa de um produto pesquisado pelo nome, com busca parcial
	 * 
	 * @param requestProductSearchName
	 * @return listProduct
	 */
	public List<ProductModelDAO> getListProductSearch(ProductModelDAO requestProductSearchName) {
		List<ProductModelDAO> listProduct = new ArrayList<>();

		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM tb_product WHERE product_name COLLATE utf8mb4_general_ci LIKE ?");
			stmt.setString(1, "%" + requestProductSearchName.getProductName() + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listProduct.add(new ProductModelDAO(rs.getInt("product_id"), rs.getString("product_name"),
						rs.getString("product_description"), rs.getInt("product_quantity"),
						rs.getDouble("product_value"), rs.getDouble("product_review"),
						rs.getBoolean("product_status")));
			}
			return listProduct;
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return null;

	}

}
