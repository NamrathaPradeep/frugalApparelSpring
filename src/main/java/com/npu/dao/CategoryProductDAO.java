package com.npu.dao;

import java.util.List;

import com.npu.domain.Category;
import com.npu.domain.Product;
import com.npu.exceptions.CategoryProductCheckedExcptn;

public interface CategoryProductDAO {
	public List<Category> getCategories() throws CategoryProductCheckedExcptn;

	public List<Product> getProducts();
	
	public  List<Product> getProductForCategory(int categoryId);
	
	public  List<Product> getProductByProductIds(String productIds);
	
	public void updateProductAvailabilty(List<Product> products);

}