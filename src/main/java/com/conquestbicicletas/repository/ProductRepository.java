package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.ProductModelDAO;
import com.conquestbicicletas.model.dao.UpdateStatusProductDAO;
import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class ProductRepository extends ConnectionFactory{
	/**
	 * 
	 * @return listProduct
	 */
	
	//// Retorna com o Id decrescente - LIFO
	public List<ProductModelDAO> getListProduct() {
		List<ProductModelDAO> listProduct = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_product ORDER BY id_product DESC");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listProduct.add(new ProductModelDAO(rs.getInt("id_product"), rs.getString("name_product"), 
						rs.getString("ds_product"), rs.getInt("qtd_product"), rs.getDouble("value_product") , 
						rs.getDouble("aval_product"), rs.getBoolean("status_product"), rs.getInt("fk_id_img_product")));
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
	 * Registra o usuario
	 * 
	 * @param request
	 * @return retorna TRUE para usuario registrado, e false para usuario NÃO registrado
	 */
	public boolean registerProduct(ProductModelDAO requestRegisterProduct) {
		try {
			Connection connection = super.getConnection();
				PreparedStatement stmt = connection.prepareStatement(
						"INSERT INTO tb_product (name_user, cpf_user, email_user, password_user, status_user, group_user) VALUES (?, ?, ?, AES_ENCRYPT(?, 'chave'), TRUE, ?)");
				stmt.setString(1, requestRegisterProduct.getNameUser());
				stmt.setString(2, requestRegisterProduct.getCpf());
				stmt.setString(3, requestRegisterProduct.getEmail());
				stmt.setString(4, requestRegisterProduct.getPassword());
				stmt.setInt(5, requestRegisterProduct.getGroup());

				int rows = stmt.executeUpdate();

				if (rows > 0) {
					return true;
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
	
	
	/// Atualizar Produto /// FALTAM AS IMAGENS 
		public boolean updateProduct(ProductModelDAO request) {
			boolean result = false;

			final String SQL_QUERY = "UPDATE tb_product SET cod_product = ?, name_product = ?, ds_product = ?, "
					+ "qtd_product = ? , value_product = ? , status_product = ? where cod_product = ?";
			try {
				Connection connection = super.getConnection();
				PreparedStatement updateProduct = connection.prepareStatement(SQL_QUERY);
				updateProduct.setString(1, request.getProductCode());
				updateProduct.setString(2, request.getProductName());
				updateProduct.setString(3, request.getProductDescription());
				updateProduct.setInt(4, request.getProductQuantity());
				updateProduct.setDouble(5, request.getProductValue());
				updateProduct.setInt(6, request.getStatus());
				updateProduct.setString(7, request.getProductCode());
				
				

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
				sb.append(" SET status_product = ?");
				sb.append(" WHERE cod_product = ?");

				PreparedStatement stmt = connection.prepareStatement(sb.toString());
				stmt.setInt(1, requestUpdateStatusProduct.getStatusProduct());
				stmt.setString(2, requestUpdateStatusProduct.getCodeProduct());

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
		public List<ProductModelDAO> getListProductSearchName(ProductModelDAO requestProductSearchName) {
			List<ProductModelDAO> listProduct = new ArrayList<>();
			
			try {
				Connection connection = super.getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_product WHERE name_product COLLATE utf8mb4_general_ci LIKE ?");
				stmt.setString(1, "%" + requestProductSearchName.getProductName() + "%");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					listProduct.add(new ProductModelDAO(rs.getString("cod_product"), rs.getString("name_product"), 
							rs.getString("ds_product"), rs.getInt("qtd_product"), rs.getInt("value_product") , 
							rs.getInt("status_product")));
				}
				return listProduct;
			}
			catch(Exception e){
			System.out.println("Erro" + e.getMessage());
			}
			finally{
			super.closeConnection();
			}
			return null;
			
	}

	

}
