package com.npu.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Order implements RowMapper<Order>{
	
	
	int orderId;
	double orderAmount;
	String user_id;
	Date orderDate;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	@Override
	public Order mapRow(ResultSet rs, int arg1) throws SQLException {

		Order userOrd = new Order();

		userOrd.setOrderId(rs.getInt("order_no"));
		userOrd.setOrderDate(rs.getDate("order_date"));
		userOrd.setOrderAmount(rs.getDouble("order_amt"));
		userOrd.setUser_id(rs.getString("user_id"));

		return userOrd;
	}
	
	
	
}
