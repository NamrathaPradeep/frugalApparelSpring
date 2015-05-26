package com.npu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.npu.dao.CardDAO;
import com.npu.dao.CategoryProductDAO;
import com.npu.dao.OrderAndOrderMappingDAO;
import com.npu.dao.UserDAO;
import com.npu.domain.Category;
import com.npu.domain.Product;
import com.npu.exceptions.CategoryProductCheckedExcptn;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {

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
	OrderAndOrderMappingDAO orderAndOrderMappingDaoJdbc;

	@Override
	public List<Category> getCategories() {
		List<Category> categoryFromDb = null;
		try {
			categoryFromDb = categoryProductDao.getCategories();
		} catch (CategoryProductCheckedExcptn e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryFromDb;
	}

	@Override
	public Map<Integer, List<Product>> getCategoryProductMap(
			List<Category> categoryFromDb) {

		Map<Integer, List<Product>> categoryProductMap = new HashMap<Integer, List<Product>>();

		for (Category c : categoryFromDb) {
			List<Product> products = categoryProductDao.getProductForCategory(c
					.getCategoryId());
			categoryProductMap.put(c.getCategoryId(), products);
		}

		return categoryProductMap;
	}

	@Override
	public List<Product> getProducts(String productIds) {
		// TODO Auto-generated method stub
		return categoryProductDao.getProductByProductIds(productIds);
	}

}
