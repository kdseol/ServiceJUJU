package com.stockmarket.www.service;

//Quantity -> Qty
public interface TradeService {
	/* 구매수량을 입력받아 처리한다 */
	boolean updatePurchaseQty(int qty);
	
	/* 매도수량을 입력받아 처리한다  */
	boolean updateSoldQty(int qty);
	
	/* 자산정보를 가져온다	 */
	int getAssets(int id);
	
	/* 해당종목의 자산을 가져온다 */
	int getStockAssets(int id, String stockId);
	
	/* 종목수량을 가져한다 */
	int getQty(int id, String stockId);

	/* 종목수량  및 sum 값을 변경한다 */
	void setQty(int id, String stockId, int qty, int curPrice);
	
	/*일봉/주봉/월봉 데이터를 가져온다  */
	void getStock(int date);
	
	/* 매수시 가상머니 체크 
	 * return 
	 * 	0:정상 
	 * 	1:vmoney 없음	 */
	int checkVmoney(int id, int qty, int curStockPrice);
	
	/* 주식의 소유여부를 체크하여 없으면 addHaveStock 을 실행한다 */
	boolean checkHaveStock(int id, String codeNum);
	
	/* 주식 - Zero 수량을 체크한다 */
	boolean checkZeroHaveStock(int id, String codeNum);
	/* 주식 - 마이나스 수량을 체크한다 */
	boolean checkMinusHaveStock(int id, String codeNum, int qty);
	
	/* HaveStock DB 에 데이터 추가*/
	void addHaveStock(int id, String codeNum);
	/* HaveStock DB 에 데이터 삭지*/
	void delHaveStock(int memberId, String codeNum);
	
	/* 매수 실행 */
	void tradeBuySell(int id, String codeNum, int qty, int curStockPrice);

	
}
