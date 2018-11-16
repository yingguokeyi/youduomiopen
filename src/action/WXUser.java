package action;

import action.service.MemerService;
import action.service.UserService;
import action.service.WalletService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;

/**
 * @ClassName WXUser
 * @Description TODO
 * @Author yanhuo
 * @Date 2018/11/9 18:36
 * @Version 1.0
 **/
@WebServlet(name = "WXUser", urlPatterns = "/wxUser")
public class WXUser extends BaseServlet {
    /**
     * 获取用户信息
     * @param openid
     * @return
     */
    public String getUserInfo(String openid){
        String wxMember = UserService.findWxMember(openid);
        JSONObject jsonObject = JSON.parseObject(wxMember);
        JSONArray memberArrray = jsonObject.getJSONObject("result").getJSONArray("rs");
        String parentMember = UserService.findUserInfo(memberArrray.getJSONObject(0).getString("parent_user_id"));
        String userWallet = WalletService.getUserWallet(memberArrray.getJSONObject(0).getString("id"));
        String lowerCount = MemerService.getLowerCount(memberArrray.getJSONObject(0).getString("id"));
        JSONObject json = new JSONObject();
        json.put("message","1");
        json.put("wxMember",JSON.parseObject(wxMember));
        json.put("parentMember",JSON.parseObject(parentMember));
        json.put("userWallet",JSON.parseObject(userWallet));
        json.put("lowerCount",JSON.parseObject(lowerCount));
        return json.toString();
    }
}
