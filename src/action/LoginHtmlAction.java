package action;

import action.service.LoginHtmlService;
import action.service.LoginService;
import action.service.WalletService;
import cache.BaseCache;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.RedisClient;
import servlet.BaseServlet;
import servlet.HasLog;

import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by 18330 on 2019/1/7.
 */
@WebServlet(name = "LoginHtml", urlPatterns = "/loginHtml")
public class LoginHtmlAction  extends BaseServlet {

    private static final long serialVersionUID = 1L;

    //用户登录
    @HasLog(logCode=100001)
    public static String userLoginHtml(String phone, String code) {
        String verificationCode = RedisClient.hget("phone_verification_code", phone, "verification_code");
        if(verificationCode!=null && verificationCode.equals(code)) {
            String loginHtml = LoginHtmlService.userLoginHtml(phone);
            return loginHtml;
        }else {
            return creatResult(2, "短信验证码错误", null).toString();
        }
    }

    public static String testLogin(String userId){

        String loginHtml = LoginHtmlService.getUserToken(userId);
        long time = new Date().getTime();
        System.out.println(time/1000);
        return loginHtml;
    }

    //用户注册
    public String addNewUserHtml(String phone,String code,String inviteCode,String real_name,String source) {
        String verificationCode = RedisClient.hget("phone_verification_code", phone, "verification_code");
        if(verificationCode!=null && verificationCode.equals(code)) {
            //如果填写了邀请人，但是邀请人不存在的情况下，直接返回
            if(!inviteCode.equals("")){
                //确认上级邀请人时需要判断,邀请码里的邀请人是是否存在
                Integer hasId = LoginService.getUsersgByCode(inviteCode);
                if(hasId == null) {
                    return creatResult(3, "邀请人不存在", null).toString();
                }
            }
            else {
                return creatResult(3, "请填写邀请人", null).toString();
            }

            HashMap<String, Object> map = new HashMap<String, Object>();
            Integer userId = null;

            //判断用户是否存在
            String has = LoginService.getNewUsersgByPhone(phone,source);
            JSONArray ja = JSONObject.parseObject(has).getJSONObject("result").getJSONArray("rs");
            if(ja.size() != 0) {
                return creatResult(4, "该手机号已绑定", null).toString();
            }else{
                String sixCode = LoginService.getRandomCode();
                //插入User表
                userId = LoginService.addPoseidonUser(phone, phone, source,sixCode,real_name);
                //插入邀请表
                LoginService.confirmNewSupmember(String.valueOf(userId),inviteCode);
                //插入钱包表
                WalletService.saveUserWallet(String.valueOf(userId),0,0,0, BaseCache.getTIME(),BaseCache.getTIME(),"remark");
                //获取token
                String token = LoginHtmlService.getUserToken(String.valueOf(userId));
                map.put("sixCode", sixCode);
                map.put("userId", userId);
                map.put("token", token);
                RedisClient.hdel("phone_verification_code", phone);
            }

            return creatResult(1, "", map).toString();
        }else {
            return creatResult(2, "短信验证码错误", null).toString();
        }
    }

}
