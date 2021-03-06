package action.service;

import cache.BaseCache;
import cache.ResultPoor;
import common.PropertiesConf;

/**
 * Created by 18330 on 2018/12/1.
 */
public class TaskService extends BaseService  {

    public static String getAllTask(String userId,String getDate){
        int i = sendObject(963, userId,getDate);
        return ResultPoor.getResult(i);
    }

    public static String getTaskMoney(String substringDate){
        int i = sendObject(964, substringDate);
        return ResultPoor.getResult(i);
    }

    //已领任务金额，任务数量
    public static String getUserTaskMoney(String userId){
        int i = sendObject(988, userId);
        return ResultPoor.getResult(i);
    }

    public static String getTaskNumber(String id){
        int i = sendObject(965, id);
        return ResultPoor.getResult(i);
    }

    public static String getUserTask(String userId,String status){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM ( SELECT c.id, c.category_name, c.bonus, c.`status`, c.create_time, c.task_begin_time, c.task_end_time, IFNULL(t.`status`, 0) AS state, t.create_date FROM youduomi.b_picture_category AS c LEFT JOIN youduomi.b_task_list AS t ON c.id = t.task_id where 1=1 ");
        sql.append(" and t.user_id = ").append(userId).append(" ) AS f WHERE `status` = 0");
        if ("1".equals(status)){
            sql.append(" and state IN(1, 2, 3, 4, 5, 6) ");
        } else if ("2".equals(status)) {
            sql.append(" and state = 1 ");
        } else if ("3".equals(status)) {
            sql.append(" and state = 3 ");
        } else if ("4".equals(status)) {
            sql.append(" and state IN( 2, 4, 5, 6) ");
        }
        sql.append(" ORDER BY create_date DESC ");
//        int i = sendObject(966, userId);
        int sid = BaseService.sendObjectBase(9999, sql.toString());
        return ResultPoor.getResult(sid);
    }

    public static String getStateNum(String userId){
        int i = sendObject(967, userId);
        return ResultPoor.getResult(i);
    }

    public static String getUserMoney(String userId){
        int i = sendObject(968, userId);
        return ResultPoor.getResult(i);
    }

    public static String delTask(String task_id,String userId){
        int i = sendObjectCreate(969,task_id,userId);
        String res = ResultPoor.getResult(i);
        return res;
    }

    public static String upTaskStatus(String task_id,String userId){
        String status = "0";
        int i = sendObjectCreate(970,status,task_id,userId);
        String res = ResultPoor.getResult(i);
        return res;
    }

    public static String addReport(String userId,String taskId,String type,String getDate,String remarks){
        int i = sendObjectCreate(973,userId,taskId,type,getDate,remarks);
        String res = ResultPoor.getResult(i);
        return res;
    }

    public static String getUserSingleTask(String userId,String taskId){
        int i = sendObject(974, taskId,userId);
        String result = ResultPoor.getResult(i);
        String startId = (String) TaskService.getFieldEachValue(result, "id", String.class, 0);
        return startId;
    }


    //更新开始任务
    public static String upStartTask(String userId,String taskId,String getDate,String singleTask,String state){
        int i = sendObjectCreate(975,taskId,userId,getDate,state,singleTask);
        String res = ResultPoor.getResult(i);
        return res;
    }

    //插入开始任务
    public static String addStartTask(String userId,String taskId,String getDate,String state){
        int i = sendObjectCreate(976,taskId,userId,getDate,state);
        String res = ResultPoor.getResult(i);
        return res;
    }

    public static String displayUserTask(String userId,String taskId){
        int i = sendObject(977, taskId,userId,taskId);
        return ResultPoor.getResult(i);
    }

    public static String getUserTaskInfo(String userId,String taskId){
        int i = sendObject(978, userId,taskId);
        return ResultPoor.getResult(i);
    }

    public static String getTaskImgInfo(String id){
        int i = sendObject(979, PropertiesConf.IMG_URL_PREFIX,id);
        return ResultPoor.getResult(i);
    }

    public static String getStartTaskInfo(String userId,String taskId){
        int i = sendObject(980, userId,taskId);
        return ResultPoor.getResult(i);
    }

    public static String upTaskFailStatus(String userId,String taskId){
        String getDate = BaseCache.getTIME();
        String status = "0";
        int i = sendObjectCreate(981,getDate,status,taskId,userId);
        String res = ResultPoor.getResult(i);
        return res;
    }

    public static String submitTask(String userId,String taskId,String taskImgIds){
        String submitDate = BaseCache.getTIME();
        String status = "3";
        int i = sendObjectCreate(982,submitDate,taskImgIds,status,taskId,userId);
        String res = ResultPoor.getResult(i);
        return res;
    }

    public static String searchTask(String userId,String date){
        int i = sendObject(983, userId,date);
        return ResultPoor.getResult(i);
    }


    public static String getTaskFail(String taskId,String userId){
        int i = sendObject(990,taskId,userId);
        return ResultPoor.getResult(i);
    }

    public static String getTaskAccount(String userId){
        int i = sendObject(995,userId);
        return ResultPoor.getResult(i);
    }


    //用户发布任务
    public static String addUserTaskHtml(String userId,String category_name,String link_adress,String type,String remark,String tips_words,String bonus,String task_end_time,String contrastImgIds,String check_time,String task_number,String task_time){
        String submitDate = BaseCache.getTIME();
        String status = "1";
        int source = 1;
        int i = sendObjectCreate(1006,userId,category_name,link_adress,type,source,remark,tips_words,bonus,submitDate,submitDate,status,0,task_end_time,contrastImgIds,task_number,check_time,task_time);
        return ResultPoor.getResult(i);
    }




    public static String saveTask(String categoryName,String taskNumber,String checkTime,String taskTime,String bomus,String taskEndTime,
                                  String userId,String taskBeginTime,int status,String source,String detailImgId,String contrastImgIds){
        int i = sendObjectCreate(683,categoryName,taskNumber,checkTime,taskTime,bomus,taskEndTime,userId,taskBeginTime,status,source,detailImgId,contrastImgIds);
        String res = ResultPoor.getResult(i);
        return res;
    }
}
