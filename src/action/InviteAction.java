package action;

import action.service.MemerService;
import com.alibaba.fastjson.JSONObject;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 18330 on 2018/11/14.
 */
@WebServlet(name = "Invite", urlPatterns = "/invite")
public class InviteAction extends BaseServlet {


    public String getAllInviteInfo(String userId){

        String res =  MemerService.getAllLower(userId);
        String res1 =  MemerService.getLowerCount(userId);
        String res2 =  MemerService.getLowerLowerCount(userId);

        JSONObject userList = JSONObject.parseObject(res);
        JSONObject userCount1 = JSONObject.parseObject(res1);
        JSONObject userCount2 = JSONObject.parseObject(res2);

        int size = userList.getJSONObject("result").getJSONArray("rs").size();
        int size1 = Integer.parseInt(userCount1.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("num"));
        int size2 = Integer.parseInt(userCount2.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("num"));

        HashMap<String,Object> resMap = new HashMap<String,Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        if (size != 0){
            for(int i=0;i<size;i++){

                HashMap<String,Object> resultMap = new HashMap<String,Object>();

                JSONObject jsonObject = userList.getJSONObject("result").getJSONArray("rs").getJSONObject(i);
                String id = jsonObject.getString("id");
                String wx_nick_name = jsonObject.getString("wx_nick_name");
                String phone = jsonObject.getString("phone");
                String member_level = jsonObject.getString("member_level");
                String Invitation_code = jsonObject.getString("Invitation_code");
                String head_image = jsonObject.getString("head_image");
                int num = size1+size2;
                resultMap.put("id", id);
                resultMap.put("wx_nick_name", wx_nick_name);
                resultMap.put("phone", phone);
                resultMap.put("member_level", member_level);
                resultMap.put("Invitation_code", Invitation_code);
                resultMap.put("head_image", head_image);

                resultMap.put("numAll", num);
                resultMap.put("num1", size1);
                resultMap.put("num2", size2);

                list.add(resultMap);
                resMap.put("result", list);
            }
            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
        }

        return creatResult(3, "无下级会员", null).toString();
    }

    //查询下级会员
    public String getLower(String userId){
        String res =  MemerService.getLower(userId);
        return res;
    }

    //查询下下级会员
    public String getLowerLower(String userId){
        String res =  MemerService.getLowerLower(userId);
        return res;
    }

    //查询会员详情
    public String getLowerUserInfo(String userId){
        String res =  MemerService.getLowerUserInfo(userId);
        return res;
    }

}
