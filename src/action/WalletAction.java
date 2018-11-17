package action;

import action.service.WalletService;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;

/**
 * Created by 18330 on 2018/11/16.
 */
@WebServlet(name = "Wallet", urlPatterns = "/wallet")
public class WalletAction extends BaseServlet {

    //查询钱包余额
    public String getWalletMoney(String userId){
        String res =  WalletService.getWalletMoney(userId);
        return res;
    }


    //小票上传历史记录
    public String getReceiptsRecord(String userId,String receipts_order){
        String res =  WalletService.getReceiptsRecord(userId,receipts_order);
        return res;
    }

}
