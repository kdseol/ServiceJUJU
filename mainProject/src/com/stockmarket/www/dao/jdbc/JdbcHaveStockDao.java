package com.stockmarket.www.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.stockmarket.www.dao.HaveStockDao;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.entity.HaveStock;

public class JdbcHaveStockDao implements HaveStockDao {

	
	
	@Override
	public List<HaveStock> getList(int id) {
		
		List<HaveStock> stockList = new ArrayList<>();
		
		String sql = "SELECT * FROM HAVE_STOCK WHERE MEMBER_ID = ?";		
		
		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				int memberId = rs.getInt("MEMBER_ID");
				String stockId = rs.getString("STOCK_ID");
				int quantity = rs.getInt("QUANTITY");

				HaveStock haveStock = new HaveStock(memberId, stockId, quantity);
				stockList.add(haveStock);
			}
			
			rs.close();
			st.close();	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return stockList;
	}

	@Override
	public HaveStock get(int memberId, String stockId) {

		HaveStock haveStock = null;
		
		String sql = "SELECT * FROM HAVE_STOCK WHERE MEMBER_ID = ? AND STOCK_ID = ?";		
		
		try {

			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, memberId);
			st.setString(2, stockId);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				haveStock = new HaveStock(
							rs.getInt("MEMBER_ID")
							,rs.getString("STOCK_ID")
							,rs.getInt("QUANTITY"));
			}
			rs.close();
			st.close();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return haveStock;
	}

	@Override
	public int update(HaveStock haveStock) {
		int result = 0;

		String sql = "UPDATE HAVE_STOCK SET QUANTITY=? WHERE MEMBER_ID = ? AND STOCK_ID = ?";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, haveStock.getQuantity());
			st.setInt(2, haveStock.getMemberId());
			st.setString(3, haveStock.getStockId());

			result = st.executeUpdate();

			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(HaveStock haveStock) {
		int result = 0;

		String sql = "INSERT INTO HAVE_STOCK(MEMBER_ID, STOCK_ID, QUANTITY) VALUES (?,?,?)";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, haveStock.getMemberId());
			st.setString(2, haveStock.getStockId());
			st.setInt(3, haveStock.getQuantity());

			result = st.executeUpdate();

			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int memberId, String stockId) {
		int result = 0;

		String sql = "DELETE HAVE_STOCK WHERE MEMBER_ID = ? AND STOCK_ID = ?";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, memberId);
			st.setString(2, stockId);

			result = st.executeUpdate();

			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * =======================================================================
	 * ============================= for Test ================================
	 * =======================================================================
	 */	
//	public static void main(String[] args) {
//		int testIndex = 0;
//		HaveStock haveStock = new HaveStock();
//		JdbcHaveStockDao stockDao = new JdbcHaveStockDao();
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("숫자를 입력하시오");
//		testIndex = sc.nextInt();
//
//		switch(testIndex) {
//		case 1:	//update 문 Test
//			haveStock.setMemberId(2);// 2 - dogseen@gamil.com
//			haveStock.setQuantity(500);	
//			haveStock.setStockId("095660"); 
//			stockDao.update(haveStock);
//			break;
//		}
//	}
}
