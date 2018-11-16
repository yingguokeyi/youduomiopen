package action;

import action.service.FinanceService;
import action.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.PropertiesConf;
import org.xml.sax.SAXException;
import servlet.BaseServlet;
import utils.MD5Util;
import utils.StringUtil;
import utils.WechatRequest;

import javax.servlet.annotation.WebServlet;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WechatOrderAction
 * @Description TODO
 * @Author yanhuo
 * @Date 2018/11/13 18:20
 * @Version 1.0
 **/
@WebServlet(name = "WechatOrderAction",urlPatterns = "/wechatOrder")
public class WechatOrderAction extends BaseServlet {

    //微信支付统一下单
    public Map<String, Object> unifiedorder(String openid) throws IOException, SAXException, ParserConfigurationException {
        HashMap<String, Object> wechatPayInfoMap = new HashMap<String, Object>();
        String wxMember = UserService.findWxMember(openid);
        JSONObject jsonObject = JSON.parseObject(wxMember);
        String useId = jsonObject.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("id");
        //获取财务流水号
        String transactionNo = FinanceService.getTradeNo(useId);


        wechatPayInfoMap.put("appid", PropertiesConf.APP_ID.trim());
        wechatPayInfoMap.put("mch_id", PropertiesConf.MINI_MCH_ID.trim());
        wechatPayInfoMap.put("nonce_str", StringUtil.getRandomStringByLength(32));
        //商品描述
        wechatPayInfoMap.put("body", "升级会员");
        //本系统内商户订单号
        wechatPayInfoMap.put("out_trade_no", transactionNo);
        //交易金额默认为人民币交易，接口中参数支付金额单位为【分】，参数值不能带小数。对账单中的交易金额单位为【元】
        wechatPayInfoMap.put("total_fee", "1");
        //wechatPayInfoMap.put("total_fee", MoneyUtil.moneyMulOfNotPoint(totalPrice, String.valueOf(100)));
        //支付回调地址
        //wechatPayInfoMap.put("notify_url", "http://uranus.sweetguo.top:8080/uranus/wechatNotityAction");
        wechatPayInfoMap.put("notify_url", PropertiesConf.MINI_NOTIFY_URL.trim());
        //微信小程序 选择类型
        wechatPayInfoMap.put("trade_type", "JSAPI");

        wechatPayInfoMap.put("openid", openid);
        //wechatPayInfoMap.put("openid", "oA9D20EZogOYpVNKYEjfAVUWfGaQ");

        wechatPayInfoMap.put("sign", MD5Util.sign(StringUtil.createLinkString(wechatPayInfoMap), "&key=" + PropertiesConf.MINI_APP_KEY.trim(), "UTF-8").toUpperCase());
        String xml = StringUtil.mapToXML(wechatPayInfoMap);
        WechatRequest wchatHttpsRequest = new WechatRequest();
        String responseString = wchatHttpsRequest.httpRequest(WechatRequest.UNIFIEDORDER_REQUST_URL,"POST", xml);
        Map<String, Object> resultMap = StringUtil.xml2Map(responseString);
        if (!"SUCCESS".equals(resultMap.get("return_code"))) {
            System.out.println("resultMap:"+resultMap.toString());
            return	null;
        }
        // 组装返回参数
        wechatPayInfoMap.clear();
        // 小程序ID 需要小程序appId生成数据签名，所以需要放入
        wechatPayInfoMap.put("appId", resultMap.get("appid"));
        // 随机字符串
        wechatPayInfoMap.put("nonceStr", resultMap.get("nonce_str"));
        //预支付交易会话标识
        wechatPayInfoMap.put("package", "prepay_id=" + resultMap.get("prepay_id"));
        // 时间戳
        wechatPayInfoMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        //签名方式，固定值MD5
        wechatPayInfoMap.put("signType", "MD5");
        // 将数据签名
        wechatPayInfoMap.put("paySign",
                MD5Util.sign(StringUtil.createLinkString(wechatPayInfoMap), "&key=" +  PropertiesConf.MINI_APP_KEY.trim(), "UTF-8").toLowerCase());
        // 小程序ID绝密文件 不能回传网路传输可能引起安全问题,回传需要隐藏
        wechatPayInfoMap.remove("appId");
        System.out.println("wechatPay:"+wechatPayInfoMap.toString());
        return wechatPayInfoMap;
    }
}
