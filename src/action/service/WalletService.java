package action.service;


import cache.AioTcpCache;
import cache.ResultPoor;

public class WalletService extends BaseService{
	
	public static String getBalance(String userId) {
		Integer sid = sendObject(213,userId);
		return  ResultPoor.getResult(sid); 
	}
	
	public static String getSelfCommission(String userId) {
		Integer sid = sendObject(214,userId,userId);
		return  ResultPoor.getResult(sid); 
	}
	
	public static String getCommission(String userId) {
		Integer sid = sendObject(215,userId,userId);
		return  ResultPoor.getResult(sid); 
	}
	
	public static String getTodayCommission(String userId,String today) {
		Integer sid = sendObject(216,userId,today);
		return  ResultPoor.getResult(sid); 
	}
	
	public static String getMemberProfitList(String userId,String startDate,String endDate,String orderno,Integer beginNum,Integer endNum ) {
		int orderListId;
    	String sql = " ORDER BY o.pay_date desc";
    	// 分页查询sql
		if(orderno != null && !orderno.equals("")) {
			sql =  " and o.order_no like '%" +orderno+ "%'"+ sql  ;
		}
		if(startDate != null && !startDate.equals("")) {
			sql =  " and substr(o.pay_date,1,6) >= " +startDate+ " "+ sql  ;
		}
        if(endDate != null && !endDate.equals("")){
			sql =  " and substr(o.pay_date,1,6) <= " +endDate+ " "+ sql  ;
		}
		orderListId = sendObject(AioTcpCache.ctc,190,sql,userId,beginNum,endNum);
		
		return  ResultPoor.getResult(orderListId); 
	}

	public static String getUserWallet(String userId){
		int sid = sendObject(666,userId);
		String result = ResultPoor.getResult(sid);
		return result;
	}

	public static String saveUserWallet(String userId,int money,int withdraw,int balance,String createTime,
										String editTime,String remarks) {
		int sid = sendObjectCreate(669, userId, money, withdraw, balance, createTime, editTime, remarks);
		String result = ResultPoor.getResult(sid);
		return result;
	}

	public static String getWalletMoney(String userId) {
		Integer sid = sendObject(956,userId);
		return  ResultPoor.getResult(sid);
	}


	public static String getReceiptsRecord(String userId,String receipts_order) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( SELECT r.*, e.profit FROM youduomi.b_receipts AS r LEFT JOIN youduomi.b_recommend AS e ON r.id = e.receipts_id AND r.user_id = e.user_id ) AS f WHERE 1 = 1 ");
		sql.append(" and user_id =").append(userId);
		if (receipts_order!=null && !"".equals(receipts_order) ){
			sql.append(" and receipts_order =").append(receipts_order);
		}
		sql.append(" ORDER BY create_date DESC ");
		int sid = BaseService.sendObjectBase(9999,sql.toString());
		return  ResultPoor.getResult(sid);

	}
}