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

    public static String savePeceipts(String total,String remark,String receiptsOrder,String buyNumber ,String prePayment,String money,String payType,String buyTime,String receiptsImgUrl){
        int receiptsOrderI = Integer.valueOf(receiptsOrder);
        int buyNumberI = Integer.valueOf(buyNumber);
        String createdDate = BaseCache.getTIME();
        int sid = sendObjectCreate(668, total,remark, receiptsOrderI, buyNumberI, prePayment,money, payType,buyTime,receiptsImgUrl,createdDate,0);
        String result = ResultPoor.getResult(sid);
        return result;
    }

    public static String findPeceipts(String receiptsOrder){
        int receiptsOrderI = Integer.valueOf(receiptsOrder);
        int sid = sendObject(667,receiptsOrderI);
        String result = ResultPoor.getResult(sid);
        return result;
    }
}
