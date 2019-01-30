package action.service;

import cache.ResultPoor;
import com.alibaba.fastjson.JSONObject;
import common.RedisClient;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by 18330 on 2019/1/7.
 */
public class LoginHtmlService extends BaseService {

    public static String userLoginHtml(String phone) {

        int userSid = sendObject(934, phone,"5");
        String result = ResultPoor.getResult(userSid);
        JSONObject userList = JSONObject.parseObject(result);

        int size = userList.getJSONObject("result").getJSONArray("rs").size();
        if(size == 0){
            return creatResult(3, "该手机号不存在", null).toString();
        }

//        String rsTest = ResultPoor.getResult(userSid);
        int userStatus = (int) getFieldValue(result, "status", Integer.class);
        //除以会员页面会员非会员的考虑
        String memberLevel = (String) getFieldValue(result, "member_level", String.class);
        //返回是否有上级表示，出于安全考虑只返回标识字段
        String parentUserId = (String) getFieldValue(result, "parent_user_id", String.class);
        int parentUserHasMark = 0;
        //考虑到后台修改数据时常常会产生parentUserId为空的情况，先如此处理
        if(parentUserId.equals("")||parentUserId==null||Integer.valueOf(parentUserId)==0){
            parentUserHasMark = 0;
        }else{
            parentUserHasMark = 1;
        }
        String token = "";
        if(userStatus==1) {
            token = UUID.randomUUID().toString();
            String userId = (String) getFieldValue(result, "id", String.class);
            String oldToken = RedisClient.hget("user_token",userId,"token");
            //如果已登陆过，存在redis信息，先删除
            if(oldToken != null) {
                RedisClient.hdel("token_user", oldToken) ;
                RedisClient.hdel("user_token", userId) ;
            }

            RedisClient.hset("token_user",token,"userText",result,60000000);
            RedisClient.hset("token_user",token,"userId",userId,60000000);

            RedisClient.hset("user_token",userId,"userText",result,60000000);
            RedisClient.hset("user_token",userId,"token",token,60000000);
        }
        String res = "";
        if(token!="") {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("token", token);
            map.put("memberLevel", memberLevel);
            map.put("parentUserHasMark", Integer.toString(parentUserHasMark));
            res = creatResult(1, "", map).toString();
        }else {
            res = creatResult(3, "该手机号不存在", null).toString();
        }
        return res;
    }

    public static String getUserToken(String userId){

        int userSid = sendObject(665, userId);
        String rsTest = ResultPoor.getResult(userSid);

        String token = "";
        token = UUID.randomUUID().toString();
        String oldToken = RedisClient.hget("user_token",userId,"token");
        //如果已登陆过，存在redis信息，先删除
        if(oldToken != null) {
            RedisClient.hdel("token_user", oldToken) ;
            RedisClient.hdel("user_token", userId) ;
        }

        RedisClient.hset("token_user",token,"userText",rsTest,60000000);
        RedisClient.hset("token_user",token,"userId",userId,60000000);

        RedisClient.hset("user_token",userId,"userText",rsTest,60000000);
        RedisClient.hset("user_token",userId,"token",token,60000000);
        return token;
    }
}
