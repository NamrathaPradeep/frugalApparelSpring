package com.npu.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class UserOrder implements RowMapper<UserOrder> {
	int orderId;
	double orderAmount;
	int productId;
	double productPrice;
	String productDescription;
	String productName;
	Date orderDate;
	
	public UserOrder(){
		
	}

	
	public UserOrder(double orderAmount, int productId,
			double productPrice, String productDescription, String productName,
			Date orderDate) {
		super();
		//this.orderId = orderId;
		this.orderAmount = orderAmount;
		this.productId = productId;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productName = productName;
		this.orderDate = orderDate;
	}


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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public UserOrder mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserOrder userOrd = new UserOrder();

		//userOrd.setOrderId(rs.getInt("order_no"));
		userOrd.setOrderDate(rs.getDate("order_date"));
		userOrd.setOrderAmount(rs.getDouble("order_amt"));
		userOrd.setProductId(rs.getInt("product_id"));
		userOrd.setProductPrice(rs.getDouble("product_price"));
		userOrd.setProductDescription(rs.getString("product_desc"));
		userOrd.setProductName(rs.getString("product_name"));
		

		return userOrd;

	}

}
