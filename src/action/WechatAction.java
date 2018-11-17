package action;

import action.service.UserService;
import action.service.WalletService;
import cache.BaseCache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.PropertiesConf;
import common.RedisClient;
import model.AccessToken;
import org.dom4j.DocumentException;
import servlet.BaseServlet;
import utils.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WechatAction
 * @Description TODO
 * @Author yanhuo
 * @Date 2018/11/7 10:21
 * @Version 1.0
 **/
@WebServlet(name = "WechatAction",urlPatterns = "/wechatAction")
public class WechatAction extends BaseServlet {
    public String coreService(HttpServletRequest request, HttpServletResponse response){
        String message = null;
        String method = request.getMethod();
        if(method.equals("GET")) {
            //默认返回的文本消息内容
            String content = request.getQueryString();
            System.out.println(content);
            String substring = content.substring(content.indexOf("&") + 1, content.length());
            System.out.println(substring);
            if (substring.startsWith("signature")) {
                //检查消息是否来自微信服务器
                String echostr = WechatUtil.CheckSignature(substring);

                //返回echostr给微信服务器
                OutputStream os = null;
                try {
                    os = response.getOutputStream();
                    os.write(URLEncoder.encode(echostr, "UTF-8").getBytes());
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }else if(method.equals("POST")){
            message = getMessage(request, response);
        }
        return message;
    }

    public static String getMessage(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> map = null;
        String message = null;
        try {
            map = new MessageFormat().xmlToMap(request);
            String fromUserName = map.get("FromUserName");//公众号
            String toUserName = map.get("ToUserName");//粉丝号
            String msgType = map.get("MsgType");//发送的消息类型[比如 文字,图片,语音。。。]
            String content = map.get("Content");//发送的消息内容

            System.out.println("fromUserName："+fromUserName+"   ToUserName："+toUserName+"  MsgType："+msgType+"  "+content);
            if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
                if("0".equals(content)){
                    message  = MessageFormat.initText(toUserName, fromUserName, "功能正在完善中,稍安勿躁。");
                }
            }else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){//验证是关注/取消事件
                String eventType = map.get("Event");//获取是关注还是取消
                //关注
                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
                    message = MessageFormat.initText(toUserName, fromUserName, "欢迎关注掌小龙！");
                    new Thread(){
                        public void run(){
                            System.out.println("================ Thread savewxMember ==================");
                            String wxMember = UserService.findWxMember(fromUserName);
                            JSONObject jsonObject = JSON.parseObject(wxMember);
                            JSONArray rs = jsonObject.getJSONObject("result").getJSONArray("rs");
                            if(rs.size() == 0){
                                saveWXMember(fromUserName);
                            }

                        }
                    }.start();
                }else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
                    String eventKey = map.get("EventKey");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public static void saveWXMember(String openid){
        String token = RedisClient.hget("service_datacache","weixintoken","weixintoken_datacache");
        if(null == token){
            AccessToken accessToken = WeixinUtil.getAccessToken();
            token = accessToken.getToken();
            RedisClient.hset("service_datacache","weixintoken","weixintoken_datacache",token,7000);
        }
        String weixinMemberInforUrl = PropertiesConf.WEIXIN_MEMBER_INFOR_URL.replace("ACCESS_TOKEN",token).replace("OPENID",openid);
        String weixinMember = HttpUtils.sendGet(weixinMemberInforUrl, null);
        System.out.println("weixinMember:"+weixinMember);
        JSONObject jsonObject = JSON.parseObject(weixinMember);
        String headimgurl = jsonObject.getString("headimgurl").replaceAll("\\\\","");
        System.out.println("headimgurl"+headimgurl);
        String s = UserService.weixinMemberAdd(openid, jsonObject.getString("subscribe"), jsonObject.getString("nickname"),
                jsonObject.getString("sex"), jsonObject.getString("province"), headimgurl,
                "1", jsonObject.getString("subscribe_time"));
        System.out.println("save:"+s);
        String wxMember = UserService.findWxMember(openid);
        JSONObject jsonObjects = JSON.parseObject(wxMember);
        String userId = jsonObjects.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("id");
        WalletService.saveUserWallet(userId,0,0,0, BaseCache.getTIME(),BaseCache.getTIME(),"remark");
    }

    /**
     * OAuth2.0网页授权
     * @param request
     * @param response
     */
    public void OAuthOne(HttpServletRequest request, HttpServletResponse response){
        WeixinUtil.oath(request,response,"1");
    }
    public void OAuthTwo(HttpServletRequest request, HttpServletResponse response){
        WeixinUtil.oath(request,response,"3");
    }
    public void receipt(HttpServletRequest request, HttpServletResponse response){
        WeixinUtil.oath(request,response,"4");
    }
    public void toUploadReceipts(HttpServletRequest request, HttpServletResponse response){
        WeixinUtil.oath(request,response,"5");
    }

    public void codeTOAccessToken(HttpServletRequest request, HttpServletResponse response){
        //得到code
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        String openid = WeixinUtil.codeGetAccessToken(code);
        System.out.println("openid: "+openid);
        String wxMember = UserService.findWxMember(openid);
        JSONObject jsonObject = JSON.parseObject(wxMember);
        JSONArray rs = jsonObject.getJSONObject("result").getJSONArray("rs");
        switch (state){
            case "1":
            upgradeMembers(request,response,rs);
                break;
            case "3":
            personCenter(request,response,rs);
                break;
            case "4":
                receipts(request,response,rs);
                break;
            case "5":
                toUploadReceipt(request,response,rs);
                break;
        }
    }

    /**
     * 会员升级
     * @param rs
     * @param response
     * @param request
     */
    public static void upgradeMembers(HttpServletRequest request,HttpServletResponse response,JSONArray rs){
        String member_level = rs.getJSONObject(0).getString("member_level");
        String openid = rs.getJSONObject(0).getString("openid");
        try {
            if(member_level.equals("1")){
                response.sendRedirect(request.getContextPath()+"/wechat/member.jsp?openid="+openid);
            }else{
                response.sendRedirect(request.getContextPath()+"/wechat/reminder.jsp?openid="+openid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 个人中心
     * @param request
     * @param response
     */
    public static void personCenter(HttpServletRequest request, HttpServletResponse response,JSONArray rs){
        String phone = rs.getJSONObject(0).getString("phone");
        String openid = rs.getJSONObject(0).getString("openid");
        String userId = rs.getJSONObject(0).getString("id");
        try {
            if(null == phone || "".equals(phone)){
                response.sendRedirect(request.getContextPath()+"/wechat/register.jsp?openid="+openid+"&userId="+userId);
            }else{
                response.sendRedirect(request.getContextPath()+"/wechat/mine.jsp?openid="+openid+"&userId="+userId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toUploadReceipt(HttpServletRequest request, HttpServletResponse response,JSONArray rs){
        try {
            String openid = rs.getJSONObject(0).getString("openid");
            String userId = rs.getJSONObject(0).getString("id");
            response.sendRedirect(request.getContextPath()+"/wechat/toUploadReceipts.jsp?openid="+openid+"&userId="+userId);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void receipts(HttpServletRequest request, HttpServletResponse response,JSONArray rs){
        try {
            String openid = rs.getJSONObject(0).getString("openid");
            String userId = rs.getJSONObject(0).getString("id");
            response.sendRedirect(request.getContextPath()+"/wechat/receipt.jsp?openid="+openid+"&userId="+userId);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
