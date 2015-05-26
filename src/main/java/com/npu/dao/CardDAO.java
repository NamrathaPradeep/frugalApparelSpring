package com.npu.dao;

import java.util.List;

import com.npu.domain.CardInfo;
import com.npu.exceptions.CategoryProductCheckedExcptn;

public interface CardDAO {
	public void insertCard(CardInfo cardInfo)
			throws CategoryProductCheckedExcptn;

	public void deleteCard(int cardInfoId);

	public int getCardCount();
	
	public List<CardInfo> getAllCardsForUser(String userId);

}
