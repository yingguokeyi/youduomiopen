package action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import common.PropertiesConf;
import common.RedisClient;
import model.AccessToken;
import model.ViewButton;
import servlet.BaseServlet;
import utils.HttpUtils;
import utils.WeixinUtil;

import javax.servlet.annotation.WebServlet;

/**
 * @ClassName MenuMain
 * @Description 创建微信自定义菜单
 * @Author yanhuo
 * @Date 2018/11/8 8:59
 * @Version 1.0
 **/
@WebServlet(name = "MenuMain", urlPatterns = "/menuMain")
public class MenuMain extends BaseServlet {
    static String youduom_url = PropertiesConf.YOUDUOMI_URL;
    public static void wxMenu() {
        String token = RedisClient.hget("service_datacache","weixintoken","weixintoken_datacache");
        if(null == token){
            AccessToken accessToken = WeixinUtil.getAccessToken();
            token =  accessToken.getToken();
            RedisClient.hset("service_datacache","weixintoken","weixintoken_datacache",token,7000);
        }
        ViewButton cbt=new ViewButton();
        cbt.setUrl(youduom_url+"wechatAction?method=OAuthOne");
        cbt.setName("升级会员");
        cbt.setType("view");

        ViewButton xiaopcheck=new ViewButton();
        xiaopcheck.setUrl(youduom_url+"wechatAction?method=receipt");
        xiaopcheck.setName("小票查询");
        xiaopcheck.setType("view");

        ViewButton xiaopupload=new ViewButton();
        xiaopupload.setUrl(youduom_url+"wechatAction?method=toUploadReceipts");
        xiaopupload.setName("小票上传");
        xiaopupload.setType("view");


        ViewButton vbt=new ViewButton();
        vbt.setUrl(youduom_url+"wechatAction?method=OAuthTwo");
        vbt.setName("个人中心");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(xiaopcheck);
        sub_button.add(xiaopupload);


        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "小票上传");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
        button.add(cbt);
        button.add(buttonOne);
        button.add(vbt);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println("menujson>>>>"+menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
        System.out.println("url："+url);
        try{
            String rs= HttpUtils.sendPost(url, JSON.toJSONString(menujson, SerializerFeature.DisableCircularReferenceDetect));
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }

    }
}