package com.npu.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.npu.dao.CardDAO;
import com.npu.domain.CardInfo;

@Repository("cardDaoJdbc")
@Transactional
public class CardDAOJdbcImpl implements CardDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("cardinfo")
				.usingGeneratedKeyColumns("id");

	}

	@Override
	// insert card details
	public void insertCard(CardInfo cardInfo)
			 {
		SqlParameterSource params;
		Number newId;

		params = new BeanPropertySqlParameterSource(cardInfo);
		newId = jdbcInsert.executeAndReturnKey(params);
		cardInfo.setUserId(newId.toString());

	}

	// delete card based on card info id
	public void deleteCard(int cardInfoId) {
		JdbcTemplate delete = new JdbcTemplate(dataSource);
		delete.update("DELETE from cardinfo where cardinfo_id = ?",
				new Object[] { cardInfoId });

	}

	@Override
	public int getCardCount() {
		String getCountSql = "select count(*) from cardinfo";
		return jdbcTemplate.queryForObject(getCountSql, Integer.class);
	}

	@Override
	public List<CardInfo> getAllCardsForUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
