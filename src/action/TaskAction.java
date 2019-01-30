package action;

import action.service.TaskService;
import action.service.UserService;
import cache.BaseCache;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 18330 on 2018/12/1.
 */
@WebServlet(name = "Task", urlPatterns = "/task")
public class TaskAction extends BaseServlet {

    //查询全部任务
    public String getAllTask(String userId){

        String getDate = BaseCache.getTIME();
        String substringDate = getDate.substring(0, 6);
        String allTask = TaskService.getAllTask(userId,getDate);
        String allMoney = TaskService.getTaskMoney(substringDate);

        JSONObject allTaskJson = JSONObject.parseObject(allTask);
        JSONObject allMoneyJson = JSONObject.parseObject(allMoney);
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
            String task_create_time = jsonObject.getString("create_time");
            String create_time = jsonObject.getString("create_date");
            String task_begin_time = jsonObject.getString("task_begin_time");
            String task_end_time = jsonObject.getString("task_end_time");
            String state = jsonObject.getString("state");

            String taskNumber = TaskService.getTaskNumber(id);
            JSONObject taskNumberJson = JSONObject.parseObject(taskNumber);
            String number = taskNumberJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("number");

            long i2 = 0;
            if(create_time!= null && !"".equals(create_time)){
                long i1 =Long.parseLong(create_time);
                if (Long.parseLong(create_time.substring(6,8))<23){
                    i2 = i1+10000;
                }else {
                    i2 = i1+770000;
                }
            }


            resultMap.put("id", id);
            resultMap.put("category_name", category_name);
            resultMap.put("bonus", bonus);
            resultMap.put("task_create_time", task_create_time);
            resultMap.put("create_time", create_time);
            resultMap.put("create_end_time", String.valueOf(i2));
            resultMap.put("task_begin_time", task_begin_time);
            resultMap.put("task_end_time", task_end_time);
            resultMap.put("state", state);
            resultMap.put("number", number);

            list.add(resultMap);
            resMap.put("result", list);
        }
        JSONObject jsonObject2 = allMoneyJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        String money = jsonObject2.getString("money");
        String num = jsonObject2.getString("num");
        HashMap<String,Object> resultMap2 = new HashMap<String,Object>();
        resultMap2.put("money", money);
        resultMap2.put("num", num);
        resMap.put("result2", resultMap2);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //查询我的任务
    public String getUserTask(String userId,String status){

        String getDate = BaseCache.getTIME();
        String substringDate = getDate.substring(0, 6);
        //我的任务列表
        String userTask = TaskService.getUserTask(userId,status);
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
            String task_create_time = jsonObject.getString("create_time");
            String create_time = jsonObject.getString("create_date");
            String task_begin_time = jsonObject.getString("task_begin_time");
            String task_end_time = jsonObject.getString("task_end_time");
            String state = jsonObject.getString("state");

            long i2 = 0;
            if(create_time!= null){
                long i1 =Long.parseLong(create_time);
                if (Long.parseLong(create_time.substring(6,8))<23){
                    i2 = i1+10000;
                }else {
                    i2 = i1+770000;
                }
            }

            resultMap.put("id", id);
            resultMap.put("category_name", category_name);
            resultMap.put("bonus", bonus);
            resultMap.put("task_create_time", task_create_time);
            resultMap.put("create_time", create_time);
            resultMap.put("create_end_time", String.valueOf(i2));
            resultMap.put("task_begin_time", task_begin_time);
            resultMap.put("task_end_time", task_end_time);
            resultMap.put("state", state);

            list.add(resultMap);
            resMap.put("result", list);
        }
        resMap.put("result2", resultMap2);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //删除任务
    public String delTask(String task_id,String userId){
        String res =  TaskService.upTaskStatus(task_id,userId);
        String s = TaskService.displayUserTask(userId, task_id);
        JSONObject result = JSONObject.parseObject(s);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //插入举报记录
    public String addReport(String userId,String taskId,String type,String remarks ){

        String getDate = BaseCache.getTIME();
        String res = TaskService.addReport(userId, taskId, type, getDate, remarks);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //查看用户任务详情
    public String getUserTaskInfo(String userId,String taskId,String openid){
        String wxMember = UserService.findWxMember(openid);
        JSONObject userJsonObject = JSON.parseObject(wxMember);
        String phone = userJsonObject.getJSONObject("result").getJSONArray("rs").getJSONObject(0).getString("phone");
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        //查询用户任务详情
        String info = TaskService.getUserTaskInfo(userId, taskId);
        JSONObject infoJson = JSONObject.parseObject(info);
        JSONObject jsonObject = infoJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
        String detailImgIds = jsonObject.getString("detailImgIds");
        if(!"".equals(detailImgIds)){
            String[] imgIds = detailImgIds.split(",");

            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            HashMap<String, Object> map1 = new HashMap<String, Object>();
            //查询任务图片
            for (String id : imgIds) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                String taskImgInfo = TaskService.getTaskImgInfo(id);
                JSONObject taskImgJson = JSONObject.parseObject(taskImgInfo);
                JSONObject taskImgObject = taskImgJson.getJSONObject("result").getJSONArray("rs").getJSONObject(0);
                String imgId = taskImgObject.getString("id");
                String image = taskImgObject.getString("image");
                map.put("imgId",imgId);
                map.put("image",image);
                list.add(map);
            }
            JSONObject result = JSONObject.parseObject(info);
            resMap.put("result", result);
            resMap.put("img", list);
            resMap.put("phone", phone);
        }else{
            JSONObject result = JSONObject.parseObject(info);
            resMap.put("result", result);
            resMap.put("img", null);
            resMap.put("phone", phone);
        }



        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    //开始任务
    public String startTask(String userId,String taskId,String status){

        //status 0 开始任务;1 查看任务详情;4 重新任务;
        String getDate = BaseCache.getTIME();
        String state = "1";
        //查询任务是否领取过
        String singleTask = TaskService.getUserSingleTask(userId, taskId);
        if (status.equals("0")){
            if (singleTask != null){
                String res = TaskService.upStartTask(userId, taskId,getDate,singleTask,state);
            }else {
                String s = TaskService.addStartTask(userId, taskId, getDate,state);
            }
        }else if (status.equals("4")){
            String res = TaskService.upStartTask(userId, taskId,getDate,singleTask,state);
        }
        //查询开始任务信息
        String startTaskInfo = TaskService.getStartTaskInfo(userId, taskId);
        JSONObject result = JSONObject.parseObject(startTaskInfo);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    //任务失败重新任务
    public String upTaskFailStatus(String userId,String taskId){

        String res = TaskService.upTaskFailStatus(userId, taskId);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //提交任务
    public String submitTask(String userId,String taskId,String taskImgIds){

        String res = TaskService.submitTask(userId, taskId,taskImgIds);
        JSONObject result = JSONObject.parseObject(res);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    //任务搜索
    public String searchTask(String userId,String year,String month){
        StringBuffer sTime = new StringBuffer();
        if(month.length()==1){
            month = "0"+month;
        }
        sTime.append(year.substring(2)).append(month);
        String task = TaskService.searchTask(userId, sTime.toString());
        JSONObject result = JSONObject.parseObject(task);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();
    }

    //查看审核失败详情
    public String getTaskFail(String taskId,String userId){

        String taskFail = TaskService.getTaskFail(taskId,userId);
        JSONObject result = JSONObject.parseObject(taskFail);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }

    //查询入账记录
    public String getTaskAccount(String userId){

        String startTaskInfo = TaskService.getTaskAccount(userId);
        JSONObject result = JSONObject.parseObject(startTaskInfo);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("result", result);
        return creatResult(1, "亲,数据包回来了哦...", resMap).toString();

    }
    //H5用户添加任务
    public String addUserTaskHtml(String token,String category_name,String link_adress,String type,String remark,String tips_words,String bonus,String task_end_time,String contrastImgIds,String check_time,String task_number,String task_time){
        String userId = UserService.getUserIdByToken(token);
        if (userId == null) {
            return creatResult(2, "亲，未登录....", null).toString();
        }
        String s = TaskService.addUserTaskHtml(userId,category_name, link_adress, type, remark, tips_words, bonus, task_end_time, contrastImgIds, check_time, task_number, task_time);
        return s;
    }


}
