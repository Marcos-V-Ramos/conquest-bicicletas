package com.conquestbicicletas.model.dao;

import java.util.List;

import com.conquestbicicletas.model.UserModel;

public class UserCustomerDAO extends UserModel {

	private String userGender;
	private String userBirthDate;
	private List<UserCustomerAdressDAO> userAdress;

	public UserCustomerDAO() {
		super();
	}

	public UserCustomerDAO(int userId, String userName, String userCpf, String userEmail, String userPassword,
			String userGender, String userBirthDate, List<UserCustomerAdressDAO> userAdress) {
		super(userId, userName, userCpf, userEmail, userPassword);
		this.userGender = userGender;
		this.userBirthDate = userBirthDate;
		this.userAdress = userAdress;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(String userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public List<UserCustomerAdressDAO> getUserAdress() {
		return userAdress;
	}

	public void setUserAdress(List<UserCustomerAdressDAO> userAdress) {
		this.userAdress = userAdress;
	}

}
