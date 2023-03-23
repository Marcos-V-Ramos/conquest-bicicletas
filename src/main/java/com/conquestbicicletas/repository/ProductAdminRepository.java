package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.ProductModel;
import com.conquestbicicletas.model.dao.ProductDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class ProductAdminRepository extends ConnectionFactory{
	/**
	 * 
	 * @return listProduct
	 */
	
	//// Retorna com o Id decrescente - LIFO
	public List<ProductModel> getListProduct() {
		List<ProductModel> listProduct = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_product ORDER BY id_product DESC");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				listProduct.add(new ProductModel(rs.getString("cod_product"), rs.getString("name_product"), 
						rs.getString("ds_product"), rs.getInt("qtd_product"), rs.getInt("value_product") , 
						rs.getInt("status_product")));
			}
			return listProduct;
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	
	}	
	
	
	/// Atualizar Produto /// FALTAM AS IMAGENS 
		public boolean updateProduct(ProductDAO request) {
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
				updateProduct.setBoolean(6, request.getStatus());
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
		 * Retorna a pesquisa de um produto pesquisado pelo nome, com busca parcial
		 * 
		 * @param requestProductSearchName
		 * @return listProduct
		 */
		public List<ProductModel> getListProductSearchName(ProductModel requestProductSearchName) {
			List<ProductModel> listProduct = new ArrayList<>();
			
			try {
				Connection connection = super.getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tb_product WHERE name_product COLLATE utf8mb4_general_ci LIKE ?");
				stmt.setString(1, "%" + requestProductSearchName.getProductName() + "%");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					listProduct.add(new ProductModel(rs.getString("cod_product"), rs.getString("name_product"), 
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
