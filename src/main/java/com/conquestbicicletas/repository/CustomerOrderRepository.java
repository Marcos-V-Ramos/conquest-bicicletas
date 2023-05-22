package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.ImageProductModelDAO;
import com.conquestbicicletas.model.dao.OrderDAO;
import com.conquestbicicletas.model.dao.OrderDetailsDAO;
import com.conquestbicicletas.model.dao.UserCustomerAddressDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerOrderRepository extends ConnectionFactory {
	
	/**
	 * Pega apenas as informações do pedido, sem detalhes
	 * 
	 * @param customerId
	 * @return
	 */
	public List<OrderDAO> getOrder(int customerId) {
		try {
			Connection connection = super.getConnection();
			final StringBuilder SQL_QUERY = new StringBuilder();
			SQL_QUERY.append("SELECT ");
			SQL_QUERY.append("O.id_order IDORDER, ");
			SQL_QUERY.append("O.fk_id_customer IDCUSTOMER, ");
			SQL_QUERY.append("O.fk_id_address IDADDRESS, ");
			SQL_QUERY.append("O.amount TOTAL, ");
			SQL_QUERY.append("O.freight_value FRETE, ");
			SQL_QUERY.append("O.form_payment FORMPAYMENT, ");
			SQL_QUERY.append("O.status STATUSORDER, ");
			SQL_QUERY.append("O.date_order DATEORDER, ");
			SQL_QUERY.append("A.cep CEP, ");
			SQL_QUERY.append("A.logradouro LOGRADOURO, ");
			SQL_QUERY.append("A.bairro BAIRRO, ");
			SQL_QUERY.append("A.uf UF ");
			SQL_QUERY.append("FROM tb_order O ");
			SQL_QUERY.append("LEFT JOIN tb_address A ON A.id_address = O.fk_id_address " );
			SQL_QUERY.append("WHERE fk_id_customer = ?");
			
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY.toString());
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();

			List<OrderDAO> orders = new ArrayList<>();
			
			while (rs.next()) {
				OrderDAO order = new OrderDAO();
				UserCustomerAddressDAO address = new UserCustomerAddressDAO(); 
				order.setOrderId(rs.getLong("IDORDER"));
				order.setCustomerId(rs.getInt("IDCUSTOMER"));
				order.setAmount(rs.getDouble("TOTAL"));
				order.setFreightValue(rs.getDouble("FRETE"));
				order.setFormPayment(rs.getString("FORMPAYMENT"));
				order.setStatus(rs.getString("STATUSORDER"));
				order.setDateOrder(rs.getString("DATEORDER"));
				address.setCep(rs.getString("CEP"));
				address.setLogradouro(rs.getString("LOGRADOURO"));
				address.setBairro(rs.getString("BAIRRO"));
				address.setUf(rs.getString("UF"));
				order.setAddress(address);
				orders.add(order);				
			}
			return orders;
			
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}

	
	/**
	 * Pega as informações do pedido, com detalhes detalhes
	 * 
	 * @param customerId
	 * @return
	 */
	public OrderDAO getOrderDetails(int orderId) {
		List<OrderDetailsDAO> orders = new ArrayList<>();
		try {
			Connection connection = super.getConnection();
			final StringBuilder SQL_QUERY = new StringBuilder();
			SQL_QUERY.append("SELECT ");
			SQL_QUERY.append("O.id_order IDORDER, ");
			SQL_QUERY.append("O.fk_id_customer IDCUSTOMER, ");
			SQL_QUERY.append("O.fk_id_address IDADDRESS, ");
			SQL_QUERY.append("O.amount TOTAL, ");
			SQL_QUERY.append("O.freight_value FRETE, ");
			SQL_QUERY.append("O.form_payment FORMPAYMENT, ");
			SQL_QUERY.append("O.status STATUSORDER, ");
			SQL_QUERY.append("O.date_order DATEORDER, ");
			SQL_QUERY.append("A.cep CEP, ");
			SQL_QUERY.append("A.logradouro LOGRADOURO, ");
			SQL_QUERY.append("A.bairro BAIRRO, ");
			SQL_QUERY.append("A.uf UF, ");
			SQL_QUERY.append("OD.id_item_order IDITEM, ");
			SQL_QUERY.append("OD.qtd QTD, ");
			SQL_QUERY.append("OD.fk_id_product IDPRODUCT, ");
			SQL_QUERY.append("P.product_name NAMEPRODUCT, ");
			SQL_QUERY.append("P.product_value VALOR, ");
			SQL_QUERY.append("MAX(PIMG.img_id) IDIMG, ");
			SQL_QUERY.append("MAX(PIMG.img_base64) IMG ");
			SQL_QUERY.append("FROM tb_order O ");
			SQL_QUERY.append("LEFT JOIN tb_address A ON A.id_address = O.fk_id_address " );
			SQL_QUERY.append("LEFT JOIN tb_order_item OD ON OD.fk_id_order = O.id_order " );
			SQL_QUERY.append("LEFT JOIN tb_product P ON P.product_id = OD.fk_id_product " );
			SQL_QUERY.append("LEFT JOIN tb_img_product PIMG ON PIMG.fk_product_id = OD.fk_id_product " );
			SQL_QUERY.append("WHERE O.id_order = ? ");
			SQL_QUERY.append("GROUP BY P.product_id");
			
			PreparedStatement pstmt = connection.prepareStatement(SQL_QUERY.toString());
			pstmt.setInt(1, orderId);
			ResultSet rs = pstmt.executeQuery();
			
			OrderDAO order = new OrderDAO();
			
			while (rs.next()) {
				
				UserCustomerAddressDAO address = new UserCustomerAddressDAO(); 
							
				order.setOrderId(rs.getLong("IDORDER"));
				order.setCustomerId(rs.getInt("IDCUSTOMER"));
				order.setAmount(rs.getDouble("TOTAL"));
				order.setFreightValue(rs.getDouble("FRETE"));
				order.setFormPayment(rs.getString("FORMPAYMENT"));
				order.setStatus(rs.getString("STATUSORDER"));
				order.setDateOrder(rs.getString("DATEORDER"));
				address.setCep(rs.getString("CEP"));
				address.setLogradouro(rs.getString("LOGRADOURO"));
				address.setBairro(rs.getString("BAIRRO"));
				address.setUf(rs.getString("UF"));
				order.setAddress(address);
				
				OrderDetailsDAO orderDetail = new OrderDetailsDAO();
				ImageProductModelDAO imageProduct = new ImageProductModelDAO();
				orderDetail.setIdItemOrder(rs.getLong("IDITEM"));
				orderDetail.setProductQtd(rs.getInt("QTD"));
				orderDetail.setProductName(rs.getString("NAMEPRODUCT"));
				orderDetail.setProductValue(rs.getDouble("VALOR"));
				imageProduct.setIdImage(rs.getInt("IDIMG"));
				imageProduct.setImageBase64(rs.getString("IMG"));
				orderDetail.setProductImage(imageProduct);
				
				orders.add(orderDetail);				
			}
			
			order.setItemOrder(orders);
			
			return order;
			
		} catch (Exception e) {
			log.info("[ERROR] There was an error connecting to the database: %s /n %s /n %s", e.getMessage(),
					e.getLocalizedMessage());
		} finally {
			super.closeConnection();
		}
		return null;
	}
	
	
	/**
	 * Adiciona um pedido ao cliente.
	 */
	public Long addOrder(OrderDAO order) {
		Long orderId = null;
		try {
			Connection connection = super.getConnection();

			final String SQL_QUERY = "INSERT INTO tb_order (fk_id_customer, fk_id_address, amount, freight_value, form_payment, status, date_order) "
					+ "VALUES (?, ?, ?, ?, ?,'AGUARDANDO PAGAMENTO', ?)";
			PreparedStatement stmt = connection.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, order.getCustomerId());
			stmt.setInt(2, order.getAddressId());
			stmt.setDouble(3, order.getAmount());
			stmt.setDouble(4, order.getFreightValue());
			stmt.setString(5, order.getFormPayment());
			stmt.setString(6, order.getDateOrder());
			

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet generatedId = stmt.getGeneratedKeys();

				if (generatedId.next()) {
					orderId = generatedId.getLong(1);
					
					return orderId != null ? orderId : null ;
				}

				generatedId.close();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			super.closeConnection();
		}
		return orderId;
	}
	
	/**
	 * Adiciona item ao pedido.
	 */
	public Long addOrderItem(OrderDetailsDAO order, Long idOrder) {
		Long orderId = null;
		try {
			Connection connection = super.getConnection();

			final String SQL_QUERY = "INSERT INTO tb_order_item (fk_id_product, fk_id_order, qtd) "
					+ "VALUES (?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, order.getProductId());
			stmt.setLong(2, idOrder);
			stmt.setInt(3, order.getProductQtd());
			

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet generatedId = stmt.getGeneratedKeys();

				if (generatedId.next()) {
					orderId = generatedId.getLong(1);
					
					return orderId != null ? orderId : null ;
				}

				generatedId.close();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			super.closeConnection();
		}
		return orderId;
	}
}
