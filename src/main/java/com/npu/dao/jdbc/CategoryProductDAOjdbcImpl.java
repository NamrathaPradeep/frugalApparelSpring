package com.npu.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.npu.dao.CategoryProductDAO;
import com.npu.domain.Category;
import com.npu.domain.Product;
import com.npu.exceptions.CategoryProductCheckedExcptn;

@Repository("categoryProductDaoJdbc")
@Transactional
public class CategoryProductDAOjdbcImpl implements CategoryProductDAO {
	private Category category;
	private Product product;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	public static final int AVAILABLE = 1;
	public static final int NOT_AVAILABLE = 2;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		category = new Category();
		product = new Product();

	}

	@Override
	public List<Category> getCategories() throws CategoryProductCheckedExcptn {

		String getCategoriesSql = "select category_id, category_name, category_desc "
				+ "from category";

		return jdbcTemplate.query(getCategoriesSql, category);

	}

	@Override
	public List<Product> getProducts() {
		String getProductsSql = "select product_id, product_name, product_desc, product_price, category_id, version, product_img_loc "
				+ "from product ";

		return jdbcTemplate.query(getProductsSql, product);
	}

	@Override
	public List<Product> getProductForCategory(int categoryId) {
		String getProductsForCategorySql = "select product_id, product_name, product_desc, product_price, category_id, version, product_img_loc "
				+ "from product where category_id =  ? and available = 1 ";

		return jdbcTemplate.query(getProductsForCategorySql, product,
				categoryId);
	}

	@Override
	public List<Product> getProductByProductIds(String productIds) {
		List<Product> products = null;
		String[] productsIdsSplit = productIds.split(",");

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < productsIdsSplit.length; i++) {
			builder.append("?,");
		}

		String getProductsSql = "select product_id, product_name, product_desc, product_price, category_id, version, product_img_loc "
				+ "from product where product_id in ("
				+ builder.deleteCharAt(builder.length() - 1).toString() + ")";

		return jdbcTemplate.query(getProductsSql, product, productIds);
	}

	@Override
	public void updateProductAvailabilty(List<Product> products) {
		String updatepProductSql = "UPDATE product SET available = ?, version = ?"
				+ " where product_id = ? AND version = ? ";
		
		for(Product proudct : products){
			jdbcTemplate.update(
					updatepProductSql,
					new Object[] { NOT_AVAILABLE, proudct.getVersion() + 1, proudct.getProductId(), proudct.getVersion()});
		}
		
	}

}
