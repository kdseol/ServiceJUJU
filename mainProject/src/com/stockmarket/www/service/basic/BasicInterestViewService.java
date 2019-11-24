package com.stockmarket.www.service.basic;

import java.util.List;

import com.stockmarket.www.dao.InterestViewDao;
import com.stockmarket.www.dao.jdbc.JdbcInterestViewDao;
import com.stockmarket.www.entity.InterestStocks;
import com.stockmarket.www.entity.InterestView;
import com.stockmarket.www.service.InterestViewService;

public class BasicInterestViewService implements InterestViewService {

	InterestViewDao interestviewdao;
	
	public BasicInterestViewService() {
		interestviewdao = new JdbcInterestViewDao();
	}

	@Override
	public List<InterestView> getInterestViewList(int id) {
		
		return interestviewdao.getInterestStockList(id) ;
	}


}