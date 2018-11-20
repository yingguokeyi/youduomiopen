package action.service;


import cache.AioTcpCache;
import cache.BaseCache;
import cache.ResultPoor;
import com.alibaba.fastjson.JSONObject;
import utils.StringUtil;

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

	public static String wechatWithdrawals(String userId,String amount){

		int intAmount = Integer.parseInt(amount)*100;
		String create_time = BaseCache.getTIME();
		String sixCode = StringUtil.randomCode();
		String partner_trade_no = create_time + sixCode;
		String status = "0";
		double poundage = 0.99;
		//查询钱包金额
		int sid = sendObject(959, userId);
		String result = ResultPoor.getResult(sid);
		JSONObject res = JSONObject.parseObject(result);
		int balance = Integer.parseInt(res.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("balance"));
		//提现金额符合要求
		if ( intAmount%500 == 0 && intAmount >=500 && intAmount <= balance ){
			//修改钱包余额
			int i1 = sendObjectCreate(960, balance - intAmount, userId);
			String result1 = ResultPoor.getResult(i1);
			JSONObject result_json = JSONObject.parseObject(result1);
			String success = result_json.getString("success");
			if ( "1".equals(success)){
				int sid2 = sendObjectCreate(961, partner_trade_no,userId, intAmount,"1%",(int)(intAmount*poundage),1,1,create_time);
				String result2 = ResultPoor.getResult(sid2);
				return result2;
			}
			return creatResult(2, "提现金额有误", null).toString();
		}else {
			return creatResult(2, "提现金额有误", null).toString();
		}
	}

}