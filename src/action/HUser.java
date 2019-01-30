package action;

import action.service.*;
import cache.BaseCache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import servlet.BaseServlet;
import utils.StringUtil;

import javax.servlet.annotation.WebServlet;
import java.util.*;

/**
 * Created by 18330 on 2019/1/10.
 */
@WebServlet(name = "HUser", urlPatterns = "/hUser")
public class HUser extends BaseServlet {

    //个人中心接口
    public String getUserInfo(String token){
        String useId = UserService.getUserIdByToken(token);
        if (useId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }

        String userInfo = HUserService.findUserInfo(useId);
        JSONObject jsonObject = JSON.parseObject(userInfo);
        JSONArray memberArrray = jsonObject.getJSONObject("result").getJSONArray("rs");
        String parentMember = UserService.findUserInfo(memberArrray.getJSONObject(0).getString("parent_user_id"));
        String userWallet = WalletService.getUserWallet(memberArrray.getJSONObject(0).getString("id"));
        String res1 =  MemerService.getLowerCount(memberArrray.getJSONObject(0).getString("id"));
        String res2 =  MemerService.getLowerLowerCount(memberArrray.getJSONObject(0).getString("id"));
        int size1 = Integer.parseInt(JSONObject.parseObject(res1).getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("num"));
        int size2 = Integer.parseInt(JSONObject.parseObject(res2).getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("num"));
        int num = size1+size2;
        JSONObject json = new JSONObject();
        json.put("message","1");
        json.put("hMember",JSON.parseObject(userInfo));
        json.put("parentMember",JSON.parseObject(parentMember));
        json.put("userWallet",JSON.parseObject(userWallet));
        json.put("lowerCount",num);

        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", json);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //查看会员资料
    public String getUserData(String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String userData = HUserService.getUserData(userId);
        JSONObject jsonObject = JSONObject.parseObject(userData);

        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", jsonObject);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //保存上传头像图片ID
    public String updateHeadImgId(String token,String headImgId){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String imgId = HUserService.updateHeadImgId(userId, headImgId);
        return imgId;
    }

    //修改会员资料
    public String updateUserData(String token,String wx_nick_name,String province,String city,String county,String detailed_address){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String s = HUserService.updateUserData(userId,wx_nick_name, province, city, county, detailed_address);
        return s;
    }

    //我的团队
    public String getAllInviteInfoHtml(String token){

        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }

        String res =  HUserService.getMyTeam(userId);
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
        List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();

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
                String source = jsonObject.getString("source");
                String image = jsonObject.getString("image");

                resultMap.put("id", id);
                resultMap.put("wx_nick_name", wx_nick_name);
                resultMap.put("phone", phone);
                resultMap.put("member_level", member_level);
                resultMap.put("Invitation_code", Invitation_code);
                resultMap.put("source", source);
                resultMap.put("image", image);

                list.add(resultMap);

                resMap.put("result", list);

            }
            HashMap<String,Object> resultMap2 = new HashMap<String,Object>();
            int num = size1+size2;
            resultMap2.put("numAll", num);
            resultMap2.put("num1", size1);
            resultMap2.put("num2", size2);
            list2.add(resultMap2);
            resMap.put("num", list2);

            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
        }

        return creatResult(3, "无下级会员", null).toString();
    }

    //查询下级会员
    public String getLower(String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String res =  HUserService.getLower(userId);

        JSONObject userList = JSONObject.parseObject(res);
        int size = userList.getJSONObject("result").getJSONArray("rs").size();
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        if(size!=0){
            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

        }
        return creatResult(3, "无下级会员", null).toString();
    }

    //查询下下级会员
    public String getLowerLower(String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String res =  HUserService.getLowerLower(userId);

        JSONObject userList = JSONObject.parseObject(res);
        int size = userList.getJSONObject("result").getJSONArray("rs").size();
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        if(size!=0){
            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

        }
        return creatResult(3, "无下级会员", null).toString();
    }

    //查询团队用户信息
    public String getTeamUserInfo(String userId){

        String res =  HUserService.getTeamUserInfo(userId);
        return res;
    }

    //H5查询全部任务数量
    public String getAllTaskNum(String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String substringDate = BaseCache.getTIME().substring(0, 6);
        String res = TaskService.getTaskMoney(substringDate);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //H5查询全部任务
    public String getAllTask(String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String getDate = BaseCache.getTIME();
        String allTask = HUserService.getAllTask(userId, getDate);

        JSONObject allTaskJson = JSONObject.parseObject(allTask);
        int size = allTaskJson.getJSONObject("result").getJSONArray("rs").size();
        if (size == 0){
            return creatResult(3, "无任务", null).toString();
        }
        HashMap<String,Object> resMap = new HashMap<String,Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        for (int i=0;i<size;i++){
            HashMap<String,Object> resultMap = new HashMap<String,Object>();

            JSONObject jsonObject = allTaskJson.getJSONObject("result").getJSONArray("rs").getJSONObject(i);

            String id = jsonObject.getString("id");
            String category_name = jsonObject.getString("category_name");
            String bonus = jsonObject.getString("bonus");
            String task_create_time = jsonObject.getString("update_time");//任务创建时间
            String create_time = jsonObject.getString("create_date");//用户领取任务时间
            int task_time = Integer.valueOf(jsonObject.getString("task_time")); //领取任务限时
            String task_begin_time = jsonObject.getString("task_begin_time");//任务开始时间
            String task_end_time = jsonObject.getString("task_end_time");//任务结束时间
            String task_number = jsonObject.getString("task_number");//限制任务数量

            String state = jsonObject.getString("state");

            String taskNumber = TaskService.getTaskNumber(id);
            JSONObject taskNumberJson = JSONObject.parseObject(taskNumber);
            String number = taskNumberJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("number");


            Long i2 = StringUtil.getTaskTime(create_time, task_time);

            resultMap.put("id", id);
            resultMap.put("category_name", category_name);
            resultMap.put("bonus", bonus);
            resultMap.put("task_create_time", task_create_time);
            resultMap.put("create_time", create_time);
            resultMap.put("create_end_time", String.valueOf(i2));
            resultMap.put("task_begin_time", task_begin_time);
            resultMap.put("task_end_time", task_end_time);
            resultMap.put("task_number", task_number);
            resultMap.put("state", state);
            resultMap.put("number", number);

            list.add(resultMap);
            resMap.put("result", list);
        }
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //H5查询我的任务
    public String getUserTask(String token,String status){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String getDate = BaseCache.getTIME();
        String substringDate = getDate.substring(0, 6);
        //我的任务列表
        String userTask = HUserService.getUserTask(userId,status);
        //我的任务数量
        String userNum = TaskService.getStateNum(userId);
        //今日任务数量
        String dayTask = TaskService.getUserTaskMoney(userId);
        //累计收入
        String userMoney = TaskService.getUserMoney(userId);

        JSONObject userTaskJson = JSONObject.parseObject(userTask);
        JSONObject dayTaskJson = JSONObject.parseObject(dayTask);
        JSONObject userNumJson = JSONObject.parseObject(userNum);
        JSONObject userMoneyJson = JSONObject.parseObject(userMoney);


        HashMap<String,Object> resMap = new HashMap<String,Object>();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        JSONObject jsonObject2 = userNumJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        JSONObject jsonObject3 = dayTaskJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        JSONObject jsonObject4 = userMoneyJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        String state1 = jsonObject2.getString("state1");
        String state2 = jsonObject2.getString("state2");
        String state3 = jsonObject2.getString("state3");
        String state4 = jsonObject2.getString("state4");
        String money = jsonObject3.getString("money");
        String num = jsonObject3.getString("num");
        String allMoney = jsonObject4.getString("allMoney");
        HashMap<String,Object> resultMap2 = new HashMap<String,Object>();
        resultMap2.put("state1", state1);
        resultMap2.put("state2", state2);
        resultMap2.put("state3", state3);
        resultMap2.put("state4", state4);
        resultMap2.put("money", money);
        resultMap2.put("num", num);
        resultMap2.put("allMoney", allMoney);

        int size = userTaskJson.getJSONObject("result").getJSONArray("rs").size();
        if (size == 0){
            resMap.put("result", list);
            resMap.put("result2", resultMap2);
            return creatResult(3, "无任务", resMap).toString();
        }

        for (int i=0;i<size;i++) {
            HashMap<String, Object> resultMap = new HashMap<String, Object>();

            JSONObject jsonObject = userTaskJson.getJSONObject("result").getJSONArray("rs").getJSONObject(i);

            String id = jsonObject.getString("id");
            String category_name = jsonObject.getString("category_name");
            String bonus = jsonObject.getString("bonus");
            String task_create_time = jsonObject.getString("update_time");
            String create_time = jsonObject.getString("create_date");
            int task_time = Integer.valueOf(jsonObject.getString("task_time")); //领取任务限时
            String task_begin_time = jsonObject.getString("task_begin_time");
            String task_end_time = jsonObject.getString("task_end_time");
            String state = jsonObject.getString("state");
            String task_number = jsonObject.getString("task_number");//限制任务数量


            Long i2 = StringUtil.getTaskTime(create_time, task_time);


            resultMap.put("id", id);
            resultMap.put("category_name", category_name);
            resultMap.put("bonus", bonus);
            resultMap.put("task_create_time", task_create_time);
            resultMap.put("create_time", create_time);
            resultMap.put("create_end_time", String.valueOf(i2));
            resultMap.put("task_begin_time", task_begin_time);
            resultMap.put("task_end_time", task_end_time);
            resultMap.put("state", state);
            resultMap.put("task_number",task_number);

            list.add(resultMap);
            resMap.put("result", list);
        }
        resMap.put("result2", resultMap2);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }
    //H5 插入举报记录
    public String addReport(String token,String taskId,String type,String remarks ){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String getDate = BaseCache.getTIME();
        String res = TaskService.addReport(userId, taskId, type, getDate, remarks);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //H5查询钱包余额
    public String getWalletMoney(String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String res =  WalletService.getWalletMoney(userId);
        return res;
    }


    //H5查看用户任务详情 优化
    public String getUserTaskInfoOptimization(String taskId,String token) {
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String getDate = BaseCache.getTIME();
        String sta = "0";

        //查询任务是否领取过
        String singleTask = TaskService.getUserSingleTask(userId, taskId);
        System.out.println(singleTask);
        if (singleTask == null) {
            //没有领取的插入status:0数据
            String s = TaskService.addStartTask(userId, taskId, getDate, sta);
        }
        String info = HUserService.getUserTaskInfo(userId, taskId);
        JSONObject result = JSONObject.parseObject(info);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //H5查看用户任务详情 优化
    public String getUserTaskImg(String taskId,String token){

        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String userTaskImg = HUserService.getUserTaskImg(taskId,userId);
        JSONObject infoJson = JSONObject.parseObject(userTaskImg);
        JSONObject jsonObject = infoJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        String remark = jsonObject.getString("remark");
        String source = jsonObject.getString("source");
        String status = jsonObject.getString("status");
        String create_time = jsonObject.getString("create_time");
        String create_start_time = jsonObject.getString("create_start_time");
        int task_time = Integer.valueOf(jsonObject.getString("task_time"));
        Long taskTime = StringUtil.getTaskTime(create_start_time, task_time);
        String contrastImgIds = jsonObject.getString("contrastImgIds");

        HashMap<String,Object> resMapAll = new HashMap<String,Object>();
        HashMap<String, Object> resMap = new HashMap<String, Object>();

        if ("0".equals(source)) {
            String detailImgIds = jsonObject.getString("detailImgIds");
            if (!"".equals(detailImgIds) && detailImgIds != null) {
                String[] imgIds = detailImgIds.split(",");

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                HashMap<String, Object> map1 = new HashMap<String, Object>();
                //查询任务图片
                for (String id : imgIds) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    String taskImgInfo = TaskService.getTaskImgInfo(id);
                    JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
                    JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
                    String imgId = taskImgObject.getString("id");
                    String image = taskImgObject.getString("image");
                    map.put("imgId", imgId);
                    map.put("image", image);
                    list.add(map);
                }
                resMap.put("img", list);
            }else {
                resMap.put("img", null);
            }
            resMap.put("taskTime", String.valueOf(taskTime));
            resMap.put("create_start_time",create_start_time);
            resMap.put("source",source);
            resMap.put("status",status);
            resMapAll.put("result",resMap);
            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
        } else {
            JSONObject jsStr = JSONObject.parseObject(remark);
            int size1 = jsStr.size();
            List<Object> listAll = new ArrayList<Object>();

            for (int n = 1; n <= size1; n++) {
                String ids = jsStr.getJSONObject(String.valueOf(n)).getString("ids");
                String description = jsStr.getJSONObject(String.valueOf(n)).getString("description");
                String substring = ids.substring(1, ids.length() - 1);
                List<String> lis = Arrays.asList(substring.split(","));
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                for (String string : lis) {
                    HashMap<String, Object> resultMap = new HashMap<String, Object>();
                    String remarkImg = HUserService.getRemarkImg(string);
                    JSONObject imgUrl = JSONObject.parseObject(remarkImg);
                    String image = imgUrl.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("image");
                    resultMap.put("image", image);
                    resultMap.put("description", description);
                    list.add(resultMap);
                }
                listAll.add(list);
            }
            String[] contrastImgId = contrastImgIds.split(",");
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            //查询任务图片
            for (String id2 : contrastImgId) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                String taskImgInfo = TaskService.getTaskImgInfo(id2);
                JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
                JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
                String imgId = taskImgObject.getString("id");
                String image = taskImgObject.getString("image");
                map.put("imgId", imgId);
                map.put("image", image);
                list.add(map);
            }
            resMap.put("contrastImg", list);
            resMap.put("img", listAll);
            resMap.put("taskTime", String.valueOf(taskTime));
            resMap.put("create_start_time",create_start_time);
            resMap.put("source",source);
            resMap.put("status",status);

            resMapAll.put("result", resMap);
            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
        }
    }

    //H5查看用户任务详情
    public String getUserTaskInfo(String taskId,String token) {
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String getDate = BaseCache.getTIME();
        String sta = "0";

        //查询任务是否领取过
        String singleTask = TaskService.getUserSingleTask(userId, taskId);
        System.out.println(singleTask);
        if (singleTask == null) {
            //没有领取的插入status:0数据
            String s = TaskService.addStartTask(userId, taskId, getDate, sta);
        }
        HashMap<String,Object> resMapAll = new HashMap<String,Object>();

        HashMap<String, Object> resMap = new HashMap<String, Object>();

        //查询用户任务详情
        String info = HUserService.getUserTaskInfo(userId, taskId);
        JSONObject infoJson = JSONObject.parseObject(info);
        JSONObject jsonObject = infoJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);

        String category_id = jsonObject.getString("id");
        String category_name = jsonObject.getString("category_name");
        String task_id = jsonObject.getString("taskId");
        String remark = jsonObject.getString("remark");
        String create_time = jsonObject.getString("create_time");
        String task_begin_time = jsonObject.getString("task_begin_time");
        String task_end_time = jsonObject.getString("task_end_time");
        int task_time = Integer.valueOf(jsonObject.getString("task_time"));
        Long taskTime = StringUtil.getTaskTime(create_time, task_time);
        String task_number = jsonObject.getString("task_number");
        String contrastImgIds = jsonObject.getString("contrastImgIds");
        String tips_words = jsonObject.getString("tips_words");
        String source = jsonObject.getString("source");
        String state = jsonObject.getString("state");
        String create_start_time = jsonObject.getString("create_start_time");
        String user_task_num = jsonObject.getString("user_task_num");

        if ("0".equals(source)) {
            String detailImgIds = jsonObject.getString("detailImgIds");
            if (!"".equals(detailImgIds)) {
                String[] imgIds = detailImgIds.split(",");

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                HashMap<String, Object> map1 = new HashMap<String, Object>();
                //查询任务图片
                for (String id : imgIds) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    String taskImgInfo = TaskService.getTaskImgInfo(id);
                    JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
                    JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
                    String imgId = taskImgObject.getString("id");
                    String image = taskImgObject.getString("image");
                    map.put("imgId", imgId);
                    map.put("image", image);
                    list.add(map);
                }
                resMap.put("img", list);
                resMap.put("category_id", category_id);
                resMap.put("category_name", category_name);
                resMap.put("task_id", task_id);
                resMap.put("remark", remark);
                resMap.put("create_time", create_time);
                resMap.put("task_begin_time", task_begin_time);
                resMap.put("task_end_time", task_end_time);
                resMap.put("taskTime", String.valueOf(taskTime));
                resMap.put("source", source);
                resMap.put("state", state);
                resMap.put("create_start_time", create_start_time);
            }
            resMapAll.put("result",resMap);
            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
        } else {
                JSONObject jsStr = JSONObject.parseObject(remark);
                int size1 = jsStr.size();
                List<Object> listAll = new ArrayList<Object>();

                for (int n = 1; n <= size1; n++) {
                    String ids = jsStr.getJSONObject(String.valueOf(n)).getString("ids");
                    String description = jsStr.getJSONObject(String.valueOf(n)).getString("description");
                    String substring = ids.substring(1, ids.length() - 1);
                    List<String> lis = Arrays.asList(substring.split(","));
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    for (String string : lis) {
                        HashMap<String, Object> resultMap = new HashMap<String, Object>();

                        String remarkImg = HUserService.getRemarkImg(string);

                        JSONObject imgUrl = JSONObject.parseObject(remarkImg);
                        String image = imgUrl.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("image");
                        resultMap.put("image", image);
                        resultMap.put("description", description);
                        list.add(resultMap);
                    }
                    listAll.add(list);
                }
                String[] contrastImgId = contrastImgIds.split(",");

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                HashMap<String, Object> map1 = new HashMap<String, Object>();
                //查询任务图片
                for (String id2 : contrastImgId) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    String taskImgInfo = TaskService.getTaskImgInfo(id2);
                    JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
                    JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
                    String imgId = taskImgObject.getString("id");
                    String image = taskImgObject.getString("image");
                    map.put("imgId", imgId);
                    map.put("image", image);
                    list.add(map);
                }
                resMap.put("contrastImg", list);
                resMap.put("img", listAll);
                resMap.put("category_id", category_id);
                resMap.put("category_name", category_name);
                resMap.put("task_id", task_id);
                resMap.put("remark", remark);
                resMap.put("create_time", create_time);
                resMap.put("task_begin_time", task_begin_time);
                resMap.put("task_end_time", task_end_time);
                resMap.put("taskTime", String.valueOf(taskTime));
                resMap.put("source", source);
                resMap.put("state", state);
                resMap.put("create_start_time", create_start_time);
                resMap.put("task_number", task_number);
                resMap.put("tips_words", tips_words);
                resMap.put("user_task_num",user_task_num);

                resMapAll.put("result",resMap);
                return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

            }

    }

    //H5任务搜索
    public String searchTask(String token,String year,String month){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        if(month.length()==1){
            month = "0"+month;
        }
        StringBuffer sTime = new StringBuffer();
        sTime.append(year.substring(2)).append(month);
        String task = TaskService.searchTask(userId, sTime.toString());
        JSONObject result = JSONObject.parseObject(task);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //H5提交任务
    public String submitTask(String token,String taskId,String taskImgIds,String tips_words){

        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String res = HUserService.submitTask(userId, taskId,taskImgIds,tips_words);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    //H5开始任务
    public String startTask(String token,String category_id,String status,String task_id){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        //status 0 开始任务;1 查看任务详情;4 重新任务;
        String getDate = BaseCache.getTIME();
        String state = "1";

        if (status.equals("0")){
            String res = TaskService.upStartTask(userId, category_id,getDate,task_id,state);
        }else if (status.equals("4")){
            String res = TaskService.upStartTask(userId, category_id,getDate,task_id,state);
        }
        //查询开始任务信息
        String startTaskInfo = HUserService.getStartTaskInfo(task_id);
        JSONObject result = JSONObject.parseObject(startTaskInfo);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    //删除任务
    public String delTask(String task_id,String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }

        String res =  TaskService.upTaskStatus(task_id,userId);
        String s = TaskService.displayUserTask(userId, task_id);

        JSONObject result = JSONObject.parseObject(s);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //H5查看审核失败详情
    public String getTaskFail(String taskId,String token){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String taskFail = TaskService.getTaskFail(taskId,userId);
        JSONObject result = JSONObject.parseObject(taskFail);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    public String getUserTaskNum(String token,String category_id){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String s = HUserService.getUserTaskNum(category_id);

        JSONObject result = JSONObject.parseObject(s);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //H5任务失败重新任务
    public String upTaskFailStatus(String token,String taskId){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String res = TaskService.upTaskFailStatus(userId, taskId);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //发布管理
    public String getReleaseManagement(String token,String status){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String s = HUserService.getReleaseManagement(userId,status);
        String releaseNum = HUserService.getReleaseNum(userId);

        JSONObject infoJson = JSONObject.parseObject(s);
        int size = infoJson.getJSONObject("result").getJSONArray("rs").size();

        JSONObject result = JSONObject.parseObject(s);
        JSONObject num = JSONObject.parseObject(releaseNum);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        resMap.put("num", num);
        if(size!=0 ){
            return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
        }
        return creatResult(3, "无数据...", resMap).toString();
    }


    //任务管理任务数量
    public String getUserTaskNumOptimization(String id){
        String res = HUserService.getUserTaskNumOptimization(id);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //任务管理任务详情 优化
    public String getTaskManageInfoOptimization(String id){

        String taskInfoById = HUserService.getTaskInfoById(id);
        JSONObject infoJson = JSONObject.parseObject(taskInfoById);
        JSONObject jsonObject = infoJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);

        HashMap<String,Object> resMapAll = new HashMap<String,Object>();
        List<Object> lists = new ArrayList<Object>();
        HashMap<String, Object> resMap = new HashMap<String, Object>();

        String remark = jsonObject.getString("remark");
        String contrastImgIds = jsonObject.getString("contrastImgIds");

        JSONObject jsStr = JSONObject.parseObject(remark);
        int size1 = jsStr.size();
        List<Object> listAll = new ArrayList<Object>();

        for (int n = 1; n <= size1; n++) {
            String ids = jsStr.getJSONObject(String.valueOf(n)).getString("ids");

            String description = jsStr.getJSONObject(String.valueOf(n)).getString("description");
            String substring = ids.substring(1, ids.length() - 1);
            List<String> lis = Arrays.asList(substring.split(","));
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (String string : lis) {
                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                String remarkImg = HUserService.getRemarkImg(string);
                JSONObject imgUrl = JSONObject.parseObject(remarkImg);
                String image = imgUrl.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("image");
                resultMap.put("image", image);
                resultMap.put("imageId",string);
                resultMap.put("description", description);
                list.add(resultMap);
            }
            listAll.add(list);
        }
        String[] contrastImgId = contrastImgIds.split(",");

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        //查询任务图片
        for (String id2 : contrastImgId) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String taskImgInfo = TaskService.getTaskImgInfo(id2);
            JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
            JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
            String imgId = taskImgObject.getString("id");
            String image = taskImgObject.getString("image");
            map.put("imgId", imgId);
            map.put("auditImage", image);
            list.add(map);
        }
        resMap.put("id",id);
        resMap.put("contrastImg", list);
        resMap.put("img", listAll);

        lists.add(resMap);
        resMapAll.put("result",lists);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //任务管理任务详情 头部
    public String getTaskManageInfo(String token,String id){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String taskInfoById = HUserService.getTaskInfoById(id);
        JSONObject infoJson = JSONObject.parseObject(taskInfoById);
        JSONObject jsonObject = infoJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);

        //已领取
        String userTaskNum1 = HUserService.getUserTaskNum(id);
        JSONObject infoJson1 = JSONObject.parseObject(userTaskNum1);
        JSONObject jsonObject1 = infoJson1.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        String num1 = jsonObject1.getString("num");


        //已完成
        String userTaskNum2 = HUserService.getUserTaskNum2(id);
        JSONObject infoJson2 = JSONObject.parseObject(userTaskNum2);
        JSONObject jsonObject2 = infoJson2.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        String num2 = jsonObject2.getString("num");


        HashMap<String,Object> resMapAll = new HashMap<String,Object>();
        List<Object> lists = new ArrayList<Object>();
        HashMap<String, Object> resMap = new HashMap<String, Object>();

//        String taskId = jsonObject.getString("id");
        String category_name = jsonObject.getString("category_name");
        String create_time = jsonObject.getString("create_time");
        String type = jsonObject.getString("type");
        String link_adress = jsonObject.getString("link_adress");
        String status = jsonObject.getString("status");
        String remark = jsonObject.getString("remark");
        String tips_words = jsonObject.getString("tips_words");
        String bonus = jsonObject.getString("bonus");
        String task_number = jsonObject.getString("task_number");
        String num3 = "0";
        if(!"0".equals(task_number) && task_number != null && !"".equals(task_number)){
            int i = Integer.valueOf(task_number) - Integer.valueOf(num2);
            num3 = String.valueOf(i);
        }
        String task_end_time = jsonObject.getString("task_end_time");
        String task_time = jsonObject.getString("task_time");
        String check_time = jsonObject.getString("check_time");
        String contrastImgIds = jsonObject.getString("contrastImgIds");
        String refusal_reasons = jsonObject.getString("refusal_reasons");


        JSONObject jsStr = JSONObject.parseObject(remark);
        int size1 = jsStr.size();
        List<Object> listAll = new ArrayList<Object>();

        for (int n = 1; n <= size1; n++) {
            String ids = jsStr.getJSONObject(String.valueOf(n)).getString("ids");

            String description = jsStr.getJSONObject(String.valueOf(n)).getString("description");
            String substring = ids.substring(1, ids.length() - 1);
            List<String> lis = Arrays.asList(substring.split(","));
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (String string : lis) {
                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                String remarkImg = HUserService.getRemarkImg(string);
                JSONObject imgUrl = JSONObject.parseObject(remarkImg);
                String image = imgUrl.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("image");
                resultMap.put("image", image);
                resultMap.put("imageId",string);
                resultMap.put("description", description);
                list.add(resultMap);
            }
            listAll.add(list);
        }
        String[] contrastImgId = contrastImgIds.split(",");

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        //查询任务图片
        for (String id2 : contrastImgId) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String taskImgInfo = TaskService.getTaskImgInfo(id2);
            JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
            JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
            String imgId = taskImgObject.getString("id");
            String image = taskImgObject.getString("image");
            map.put("imgId", imgId);
            map.put("auditImage", image);
            list.add(map);
        }
        resMap.put("id",id);
        resMap.put("category_name",category_name);
        resMap.put("create_time",create_time);
        resMap.put("type",type);
        resMap.put("link_adress",link_adress);
        resMap.put("status",status);
        resMap.put("tips_words",tips_words);
        resMap.put("bonus",bonus);
        resMap.put("task_number",task_number);
        resMap.put("task_end_time",task_end_time);
        resMap.put("task_time",task_time);
        resMap.put("check_time",check_time);
        resMap.put("contrastImg", list);
        resMap.put("img", listAll);
        resMap.put("refusal_reasons", refusal_reasons);
        resMap.put("num1",num1);
        resMap.put("num2",num2);
        resMap.put("num3",num3);

        lists.add(resMap);
        resMapAll.put("result",lists);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    //修改待发布任务
    public String updateTaskInfo(String token,String category_name,String link_adress,String type,String remark,String tips_words,String bonus,String task_number,String task_end_time,String task_time,String check_time,String contrastImg,String id){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String update_time = BaseCache.getTIME();
        String taskInfo = HUserService.updateTaskInfo(category_name,link_adress,type,remark,tips_words,bonus,update_time,task_number,task_end_time,task_time,check_time,contrastImg,id);

        JSONObject result = JSONObject.parseObject(taskInfo);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //审核任务回显
    public String getAuditFailure(String id){

        String taskInfoById = HUserService.getTaskInfoById(id);
        JSONObject infoJson = JSONObject.parseObject(taskInfoById);
        JSONObject jsonObject = infoJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);

        HashMap<String,Object> resMapAll = new HashMap<String,Object>();
        List<Object> lists = new ArrayList<Object>();
        HashMap<String, Object> resMap = new HashMap<String, Object>();

        String category_name = jsonObject.getString("category_name");
        String type = jsonObject.getString("type");
        String link_adress = jsonObject.getString("link_adress");

        String create_time = jsonObject.getString("create_time");
        String status = jsonObject.getString("status");
        String remark = jsonObject.getString("remark");
        String tips_words = jsonObject.getString("tips_words");
        String bonus = jsonObject.getString("bonus");
        String task_number = jsonObject.getString("task_number");
        String task_end_time = jsonObject.getString("task_end_time");
        String task_time = jsonObject.getString("task_time");
        String check_time = jsonObject.getString("check_time");
        String contrastImgIds = jsonObject.getString("contrastImgIds");
        String refusal_reasons = jsonObject.getString("refusal_reasons");

        JSONObject jsStr = JSONObject.parseObject(remark);
        int size1 = jsStr.size();
        List<Object> listAll = new ArrayList<Object>();

        for (int n = 1; n <= size1; n++) {
            String ids = jsStr.getJSONObject(String.valueOf(n)).getString("ids");

            String description = jsStr.getJSONObject(String.valueOf(n)).getString("description");
            String substring = ids.substring(1, ids.length() - 1);
            List<String> lis = Arrays.asList(substring.split(","));
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (String string : lis) {
                HashMap<String, Object> resultMap = new HashMap<String, Object>();
                String remarkImg = HUserService.getRemarkImg(string);
                JSONObject imgUrl = JSONObject.parseObject(remarkImg);
                String image = imgUrl.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("image");
                resultMap.put("image", image);
                resultMap.put("imageId",string);
                resultMap.put("description", description);
                list.add(resultMap);
            }
            listAll.add(list);
        }
        String[] contrastImgId = contrastImgIds.split(",");

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        //查询任务图片
        for (String id2 : contrastImgId) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String taskImgInfo = TaskService.getTaskImgInfo(id2);
            JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
            JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
            String imgId = taskImgObject.getString("id");
            String image = taskImgObject.getString("image");
            map.put("imgId", imgId);
            map.put("auditImage", image);
            list.add(map);
        }
        resMap.put("id",id);
        resMap.put("category_name",category_name);
        resMap.put("link_adress",link_adress);
        resMap.put("type",type);
        resMap.put("create_time",create_time);
        resMap.put("status",status);
        resMap.put("tips_words",tips_words);
        resMap.put("bonus",bonus);
        resMap.put("task_number",task_number);
        resMap.put("task_end_time",task_end_time);
        resMap.put("task_time",task_time);
        resMap.put("check_time",check_time);
        resMap.put("contrastImg", list);
        resMap.put("img", listAll);
        resMap.put("refusal_reasons", refusal_reasons);

        lists.add(resMap);
        resMapAll.put("result",lists);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

}
