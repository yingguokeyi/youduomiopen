package action.service;

import cache.BaseCache;
import cache.ResultPoor;

/**
 * @ClassName ReceiptsService
 * @Description TODO
 * @Author yanhuo
 * @Date 2018/11/16 10:55
 * @Version 1.0
 **/
public class ReceiptsService extends BaseService{


   /* public static String savePeceipts(String total,String remark,String receiptsOrder,String buyNumber ,String prePayment,
                                      String money,String payType,String buyTime,String receiptsImgUrl,String userId ) {
        int receiptsOrderI = Integer.valueOf(receiptsOrder);
        int buyNumberI = Integer.valueOf(buyNumber);
        int sid = sendObjectCreate(668, total, remark, receiptsOrderI, buyNumberI, prePayment, money, payType, buyTime, receiptsImgUrl, userId);
        String result = ResultPoor.getResult(sid);
        return result;
    }*/
    public static String savePeceipts(String total,String remark,String receiptsOrder,String buyNumber ,String prePayment,String money,String payType,String buyTime,String receiptsImgUrl,String userId){
        int receiptsOrderI = Integer.valueOf(receiptsOrder);
        int buyNumberI = Integer.valueOf(buyNumber);
        String createdDate = BaseCache.getTIME();
        String status = "0";
        int sid = sendObjectCreate(668, total,remark, receiptsOrderI, buyNumberI, prePayment,money, payType,buyTime,receiptsImgUrl,createdDate,userId,status);
        String result = ResultPoor.getResult(sid);
        return result;
    }

    public static String findPeceipts(String receiptsOrder){
        int receiptsOrderI = Integer.valueOf(receiptsOrder);
        int sid = sendObject(667,receiptsOrderI);
        String result = ResultPoor.getResult(sid);
        return result;
    }

    public static String getReceiptsRecord(String userId,String receipts_order,String status) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM ( SELECT r.*, e.profit FROM youduomi.b_receipts AS r LEFT JOIN youduomi.b_recommend AS e ON r.id = e.receipts_id AND r.user_id = e.user_id ) AS f WHERE 1 = 1 ");
        sql.append(" and user_id =").append(userId);
        if (receipts_order!=null && !"".equals(receipts_order) ){
            sql.append(" and receipts_order =").append(receipts_order);
        }else{
            sql.append(" AND history IS NULL ");
        }
        if ("1".equals(status) ){
            sql.append(" ORDER BY create_date DESC ");
        }else{
            sql.append(" ORDER BY create_date ASC ");
        }
        int sid = BaseService.sendObjectBase(9999,sql.toString());
        return  ResultPoor.getResult(sid);
    }

    public static String emptyHistory(String userId){
        int sid = sendObjectCreate(958,1,userId);
        String result = ResultPoor.getResult(sid);
        return result;
    }

}
