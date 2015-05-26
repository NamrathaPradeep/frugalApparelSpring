package com.npu.service;

import java.util.List;
import java.util.Map;

import com.npu.domain.Category;
import com.npu.domain.Product;

public interface CategoryProductService {

	public List<Category> getCategories();

	public Map<Integer, List<Product>> getCategoryProductMap(
			List<Category> categoryFromDb);

	public List<Product> getProducts(String productIds);

}
