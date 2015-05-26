package com.npu.service;

import com.npu.domain.CardInfo;
import com.npu.domain.Order;

public interface CheckoutService {
	
	public int placeOrder(String productsIdsInRequest, double amount,
			CardInfo cardInfo, String userId);
	
	public Order getOrder(int orderId);

}
