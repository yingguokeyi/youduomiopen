package action;

import action.service.UploadImgService;
import com.alibaba.fastjson.JSONObject;
import common.PropertiesConf;
import servlet.BaseServlet;
import utils.UploadImageUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 18330 on 2018/12/10.
 */
@WebServlet(name = "UploadImg", urlPatterns = "/uploadImg")
public class UploadImgAction extends BaseServlet {

    //任务图片上传
    public String uploadTaskImg(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String address = "";

        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYYMM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        Date date = new Date();

        String pathTemp = sdf1.format(date) +"/"+sdf2.format(date)+"/";
//        String uploadTaskImagePath = "/usr/local/tomcat/tomcat_cronus/apache-tomcat-9.0.6/webapps/hestia/task/";
        address = PropertiesConf.UPLOAD_GOODS_IMAGE_PATH + pathTemp + "gdemiopen/";
        String fileName =  uploadIMG(req,address);
        String imgPath = "/gdemiopen/" + fileName;

        String res = UploadImgService.addImg(fileName, imgPath, "", "", "", "");
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //单张图片上传
    public String uploadIMG(HttpServletRequest req,String address){

        //创建目录
        createDir(address);

        InputStream is = null;
        String newfileName = "";
        try {
            is = req.getInputStream();
            StringBuffer sb = new StringBuffer();
            int count = 0;
            while(true){
                int a = is.read();
                sb.append((char)a);
                if(a == '\r')
                    count++;
                if(count==4){
                    is.read();
                    break;
                }
            }
            String title = sb.toString();
            System.out.println("title>>>>>"+title);
            String[] titles = title.split("\r\n");
            String fileName = titles[1].split(";")[2].split("=")[1].replace("\"","");
            System.out.println(fileName);
            //时间为fileName前缀
            long time = new Date().getTime();
            String postfix = fileName.split("\\.")[1].toString();//后缀 文件格式
            newfileName = String.valueOf(time)+"."+postfix;
            FileOutputStream os = new FileOutputStream(new File(address + newfileName));
            byte[] ob = new byte[1024];
            int length = 0;
            while((length = is.read(ob, 0, ob.length))>0){
                os.write(ob,0,length);
                os.flush();
            }
            os.close();
            is.close();
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return newfileName;
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
}
