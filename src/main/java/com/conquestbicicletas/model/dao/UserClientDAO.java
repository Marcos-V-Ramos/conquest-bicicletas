package com.conquestbicicletas.model.dao;

import java.util.Calendar;
import java.util.List;

import com.conquestbicicletas.model.UserModel;

public class UserClientDAO extends UserModel {

	private String userGender;
	private Calendar userBirthDate;
	private List<UserAdressDAO> userAdress;

	public UserClientDAO() {
		super();
	}

	public UserClientDAO(int userId, String userName, String userCpf, String userEmail, String userPassword,
			String userGender, Calendar userBirthDate, List<UserAdressDAO> userAdress) {
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

	public Calendar getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(Calendar userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public List<UserAdressDAO> getUserAdress() {
		return userAdress;
	}

	public void setUserAdress(List<UserAdressDAO> userAdress) {
		this.userAdress = userAdress;
	}

}
