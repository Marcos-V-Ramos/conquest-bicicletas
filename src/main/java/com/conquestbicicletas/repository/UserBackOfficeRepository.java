package com.conquestbicicletas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;


import org.springframework.stereotype.Repository;

import com.conquestbicicletas.model.dao.UserBackOfficeDAO;
import com.conquestbicicletas.repository.config.ConnectionFactory;

@Repository
public class UserBackOfficeRepository extends ConnectionFactory {	
	
	public boolean updateStatusUser(UserBackOfficeDAO requestUpdateStatus) {
		try {
			Connection connection = super.getConnection();
			
			if(requestUpdateStatus.getCpf() != null) {
				PreparedStatement stmt = connection.prepareStatement("UPDATE tb_user SET status_user = ? WHERE cpf_user = ?");
				stmt.setBoolean(1, requestUpdateStatus.getStatus());
				stmt.setString(2, requestUpdateStatus.getCpf());
				int rows = stmt.executeUpdate();
				
				if (rows > 0) {
					return true;
				}
				
				return false;
			}			
		} catch (Exception e) {
			System.out.println("Erro" + e.getMessage());
		} finally {
			super.closeConnection();
		}
		return false;
	}
}
