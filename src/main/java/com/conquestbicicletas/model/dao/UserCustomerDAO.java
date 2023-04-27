package com.conquestbicicletas.model.dao;

import java.util.List;

import com.conquestbicicletas.model.UserModel;

public class UserCustomerDAO extends UserModel {

	private String userGender;
	private String userBirthDate;
	private List<UserCustomerAddressDAO> userAddress;

	public UserCustomerDAO() {
		super();
	}

	public UserCustomerDAO(int userId, String userName, String userCpf, String userEmail, String userPassword,
			String userGender, String userBirthDate, List<UserCustomerAddressDAO> userAddress) {
		super(userId, userName, userCpf, userEmail, userPassword);
		this.userGender = userGender;
		this.userBirthDate = userBirthDate;
		this.userAddress = userAddress;
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

	public List<UserCustomerAddressDAO> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<UserCustomerAddressDAO> userAddress) {
		this.userAddress = userAddress;
	}

}
