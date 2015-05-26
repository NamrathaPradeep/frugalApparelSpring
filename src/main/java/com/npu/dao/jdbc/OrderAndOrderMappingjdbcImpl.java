package com.npu.dao.jdbc;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.npu.dao.OrderAndOrderMappingDAO;
import com.npu.domain.Order;
import com.npu.domain.UserOrder;

@Repository("orderAndOrderMappingDaoJdbc")
@Transactional
public class OrderAndOrderMappingjdbcImpl implements OrderAndOrderMappingDAO {
	private UserOrder userOrd;
	private Order order;
	

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	private SimpleJdbcInsert jdbcInsert;
	private SimpleJdbcInsert jdbcProductInsert;
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);

		userOrd = new UserOrder();
		order = new Order();

		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("orders")
				.usingColumns("order_date", "order_amt", "user_id")
				.usingGeneratedKeyColumns("order_no");

		jdbcProductInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("order_product_mapping")
				.usingColumns("order_id", "product_id")
				.usingGeneratedKeyColumns("order_product_mappingId");

	}

	@Override
	public int insertOrder(double orderAmount, String userId) {

		Number orderId = -1;

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("order_date", new Date(Calendar.getInstance()
				.getTimeInMillis()));
		params.addValue("order_amt", orderAmount);
		params.addValue("user_id", userId);
		orderId = jdbcInsert.executeAndReturnKey(params);

		return orderId.intValue();
	}

	@Override
	public void insertOrderProductMapping(int orderId,
			List<Integer> productIds) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("order_id", orderId);

		for (int productId : productIds) {
			params.addValue("product_id", productId);
			jdbcProductInsert.execute(params);
		}
	}

	@Override
	public Order getOrderByOrderId(int orderId) {

		String getOrderDetailsSql = "select order_no, order_amt, order_date, user_id "
				+ "from orders " + "where order_no = ? ";

		Order orderFromDb = jdbcTemplate.queryForObject(getOrderDetailsSql,
				order, orderId);

		return orderFromDb;

	}

	@Override
	public List<UserOrder> getAllOrdersForUser(String userId) {

		String getOrderDetailsSql = "select o.order_no, o.order_date, o.order_amt, p.product_id, p.product_name, p.product_price "
				+ "from orders o, product p, order_product_mapping op "
				+ "where o.user_id = ? "
				+ "and o.order_no = op.order_id "
				+ "and op.product_id = p.product_id";

		 List<UserOrder> userOrders =  jdbcTemplate.query(getOrderDetailsSql, userOrd,
				userId);
		 
		 return userOrders;

	}

}
