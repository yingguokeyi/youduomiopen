package action;

import action.service.ReceiptsService;
import action.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.PropertiesConf;
import common.ResultJSONUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import servlet.BaseServlet;
import utils.UploadImageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName ReceiptsAction
 * @Description TODO
 * @Author yanhuo
 * @Date 2018/11/15 10:08
 * @Version 1.0
 **/
@WebServlet(name = "ReceiptsAction", urlPatterns = "/receipts")
public class ReceiptsAction extends BaseServlet{

    public String uploadImg(HttpServletRequest request, HttpServletResponse response){
        try {
            //aliyun图片识别
            Map<String, String> map = UploadImageUtil.uploadImg(request);
            String openid = map.get("openid");
            String receiptsNo = map.get("receiptsNo");
            String remark = map.get("remark");
            //String token = map.get("token");
            String money = map.get("money");
            String buyNumber = map.get("buyNumber");
            String prePayment = map.get("prePayment");
            String payTime = map.get("payTime");
            String payType = map.get("payType");
            String title = map.get("title");
            String imagePath = map.get("imagePath");
            System.out.println("小票号："+receiptsNo+"  openid:"+openid+"  image:"+imagePath);
            if(null == receiptsNo ||null == remark || null == money||null == buyNumber||null==prePayment||null == payTime||null == payType||null == title){
                return ResultJSONUtil.faile("请重新上传清晰小票");
            }
            String peceipts = ReceiptsService.findPeceipts(receiptsNo);
            JSONObject jsonObject = JSON.parseObject(peceipts);
            JSONArray rs = jsonObject.getJSONObject("result").getJSONArray("rs");
            if(rs.size() == 0){
                String wxMember = UserService.findWxMember(openid);
                JSONObject jsonObjects = JSON.parseObject(wxMember);
                String userId = jsonObjects.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("id");
                //保存小票
                ReceiptsService.savePeceipts(title, remark, receiptsNo, buyNumber, prePayment, money, payType, payTime, imagePath,userId);
                return ResultJSONUtil.success(receiptsNo);
            }
            return ResultJSONUtil.faile("该小票已上传,请勿重复上传");
        }catch (Exception e){
            e.printStackTrace();
            return ResultJSONUtil.faile("系统繁忙请稍后再试...");
        }
    }

}
