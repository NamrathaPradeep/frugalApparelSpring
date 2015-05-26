package com.npu.dao;

import java.util.List;

import com.npu.domain.Order;
import com.npu.domain.UserOrder;

public interface OrderAndOrderMappingDAO {
	public int insertOrder(double orderAmount, String userId);

	public void insertOrderProductMapping(int orderId,
			List<Integer> productIds);

	public Order getOrderByOrderId(int orderId);

	public List<UserOrder> getAllOrdersForUser(String userId);

}
