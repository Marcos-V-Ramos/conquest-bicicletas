package com.conquestbicicletas.model.dao;

import java.util.List;

public class OrderDAO {

	private long orderId;
	private int customerId;
	private double amount;
	private int addressId;
	private double freightValue;
	private String formPayment;
	private String status;
	private String dateOrder;
	private UserCustomerAddressDAO address;
	private List<OrderDetailsDAO> itemOrder;
	
	public OrderDAO() {
	}

	public OrderDAO(long orderId, int customerId, double amount, int addressId, double freightValue,
			String formPayment, String status, String dateOrder, UserCustomerAddressDAO address, List<OrderDetailsDAO> itemOrder) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.amount = amount;
		this.addressId = addressId;
		this.freightValue = freightValue;
		this.formPayment = formPayment;
		this.status = status;
		this.dateOrder = dateOrder;
		this.address = address;
		this.itemOrder = itemOrder;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public double getFreightValue() {
		return freightValue;
	}

	public void setFreightValue(double freightValue) {
		this.freightValue = freightValue;
	}

	public String getFormPayment() {
		return formPayment;
	}

	public void setFormPayment(String formPayment) {
		this.formPayment = formPayment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}

	public UserCustomerAddressDAO getAddress() {
		return address;
	}

	public void setAddress(UserCustomerAddressDAO address) {
		this.address = address;
	}

	public List<OrderDetailsDAO> getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(List<OrderDetailsDAO> itemOrder) {
		this.itemOrder = itemOrder;
	}

}