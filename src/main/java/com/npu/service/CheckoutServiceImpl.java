package com.npu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.npu.dao.CardDAO;
import com.npu.dao.CategoryProductDAO;
import com.npu.dao.OrderAndOrderMappingDAO;
import com.npu.dao.UserDAO;
import com.npu.domain.CardInfo;
import com.npu.domain.Order;
import com.npu.domain.Product;
import com.npu.exceptions.CategoryProductCheckedExcptn;


@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	@Qualifier("userDaoJdbc")
	private UserDAO userDAO;
	
	@Autowired
	@Qualifier("categoryProductDaoJdbc")
	private CategoryProductDAO categoryProductDao;

	@Autowired
	@Qualifier("cardDaoJdbc")
	private CardDAO cardDao;
	
	@Autowired
	@Qualifier("orderAndOrderMappingDaoJdbc")
	private OrderAndOrderMappingDAO orderAndOrderMappingDao;	
	
	@Override
	public int placeOrder(String productsIdsInRequest, double amount,
			CardInfo cardInfo, String userId) {
		List<Integer> productIds = new ArrayList<Integer>();
		int orderId = 0;

		for (String p : productsIdsInRequest.split(",")) {
			productIds.add(Integer.parseInt(p));
		}

		List<Product> productsFromDB = categoryProductDao
				.getProductByProductIds(productsIdsInRequest);

		List<CardInfo> userCardInfo = cardDao.getAllCardsForUser(userId);

		boolean cardPresent = false;

		if (userCardInfo != null) {
			for (CardInfo c : userCardInfo) {
				if (c.getCardNo().equals(cardInfo.getCardNo())) {
					cardPresent = true;
					break;

				}
			}
		}

		if (!cardPresent) {
			try {
				cardDao.insertCard(cardInfo);
			} catch (CategoryProductCheckedExcptn e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// create order
		orderId = orderAndOrderMappingDao.insertOrder(amount, userId);

		// 4. insert order/product mapping
		orderAndOrderMappingDao.insertOrderProductMapping(orderId,
				productIds);

		// TODO: 5. update product availability
		categoryProductDao.updateProductAvailabilty(productsFromDB);

		return orderId;
	}

	@Override
	public Order getOrder(int orderId) {
		// TODO Auto-generated method stub
		return orderAndOrderMappingDao.getOrderByOrderId(orderId);
	}

}
