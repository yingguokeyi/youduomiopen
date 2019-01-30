package action.service;

import cache.BaseCache;
import cache.ResultPoor;
import common.PropertiesConf;

/**
 * Created by 18330 on 2019/1/10.
 */
public class HUserService extends BaseService  {

    public static String getUserData(String userId){
        int sid = sendObject(1007, PropertiesConf.IMG_URL_PREFIX,userId);
        String result = ResultPoor.getResult(sid);
        return result;
    }

    public static String updateHeadImgId(String userId,String headImgId){
        int i = sendObjectCreate(1009,headImgId,userId);
        String result = ResultPoor.getResult(i);
        return result;
    }

    public static String updateUserData(String userId,String wx_nick_name,String province,String city,String county,String detailed_address){
        int sid = sendObjectCreate(1008,wx_nick_name,province,city,county,detailed_address,userId);
        String result = ResultPoor.getResult(sid);
        return result;
    }

    public static String findUserInfo(String userId){
        int sid = sendObject(1010, PropertiesConf.IMG_URL_PREFIX,userId);
        String result = ResultPoor.getResult(sid);
        return result;
    }

    public static String getMyTeam(String userId){
        int i = sendObject(1011, PropertiesConf.IMG_URL_PREFIX,userId,PropertiesConf.IMG_URL_PREFIX,userId);
        return ResultPoor.getResult(i);
    }


    public static String getTeamUserInfo(String userId){
        int i = sendObject(1012, userId,userId,PropertiesConf.IMG_URL_PREFIX,userId);
        return ResultPoor.getResult(i);
    }

    public static String getLower(String useId) {
        int i = sendObject(1013,PropertiesConf.IMG_URL_PREFIX, useId);
        return ResultPoor.getResult(i);
    }

    public static String getLowerLower(String useId) {
        int i = sendObject(1014,PropertiesConf.IMG_URL_PREFIX,useId);
        return ResultPoor.getResult(i);
    }

    public static String getAllTask(String userId,String getDate){
        int i = sendObject(1017, userId,getDate,getDate);
        return ResultPoor.getResult(i);
    }

    public static String getUserTaskInfo(String userId,String taskId){
        int i = sendObject(1018, taskId,userId,taskId);
        return ResultPoor.getResult(i);
    }

    public static String getRemarkImg(String imgId){
        int sid = sendObject(979, PropertiesConf.IMG_URL_PREFIX,imgId);
        return ResultPoor.getResult(sid);
    }

    public static String submitTask(String userId,String taskId,String taskImgIds,String tips_words){
        String submitDate = BaseCache.getTIME();
        String status = "3";
        int i = sendObjectCreate(1019,submitDate,taskImgIds,status,tips_words,taskId,userId);
        String res = ResultPoor.getResult(i);
        return res;
    }

    public static String getStartTaskInfo(String taskId){
        int i = sendObject(1020,taskId);
        return ResultPoor.getResult(i);
    }

    public static String getUserTask(String userId,String status){
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM ( SELECT c.id, c.category_name, c.bonus, c.`status`, c.create_time, c.update_time, c.task_begin_time, c.task_end_time, IFNULL(c.task_number, 0) AS task_number, IFNULL(c.task_time, 1) AS task_time, IFNULL(t.`status`, 0) AS state, t.create_date FROM youduomi.b_picture_category AS c LEFT JOIN youduomi.b_task_list AS t ON c.id = t.task_id WHERE 1 = 1 ");
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
        String getDate = BaseCache.getTIME();
//        sql.append(" and ").append(getDate).append(" > task_begin_time AND ( ").append(getDate).append(" < task_end_time OR task_number != 0 ) ");

        sql.append(" ORDER BY update_time DESC ");
        int sid = BaseService.sendObjectBase(9999, sql.toString());
        return ResultPoor.getResult(sid);
    }

    public static String getUserTaskNum(String category_id){
        int i = sendObject(1021,category_id);
        return ResultPoor.getResult(i);
    }

    public static String getUserTaskNum2(String category_id){
        int i = sendObject(1027,category_id);
        return ResultPoor.getResult(i);
    }

    public static String getReleaseManagement(String userId,String status){
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT id ,category_name,`status`,source,bonus,task_end_time,IFNULL(task_number, 0) task_number, ( SELECT COUNT(id) FROM youduomi.b_task_list WHERE task_id = b_picture_category.id and `status`= 5 ) AS num FROM youduomi.b_picture_category  WHERE source = 1 ");
        sql.append(" AND uploader = ").append(userId);
        if ("1".equals(status)){
            sql.append(" and status IN(0,1,2,3) ");
        } else if ("2".equals(status)) {
            sql.append(" and status = 0 ");
        } else if ("3".equals(status)) {
            sql.append(" and status = 1 ");
        } else if ("4".equals(status)) {
            sql.append(" and status = 3 ");
        }
//        sql.append(" GROUP BY task_id ");
        int sid = BaseService.sendObjectBase(9999, sql.toString());
        return ResultPoor.getResult(sid);
    }

    public static String getReleaseNum(String userId){
        int i = sendObject(1024,userId);
        return ResultPoor.getResult(i);
    }

    public static String getTaskInfoById(String id){
        int i = sendObject(1025,id);
        return ResultPoor.getResult(i);
    }


    public static String updateTaskInfo(String category_name,String link_adress,String type,String remark,String tips_words,String bonus,String update_time,String task_number,String task_end_time,String task_time,String check_time,String contrastImg,String id){

        String status="1";
        int sid = sendObjectCreate(1026,category_name,link_adress,type,remark,tips_words,bonus,update_time,status,task_number,task_end_time,task_time,check_time,contrastImg,id);
        String result = ResultPoor.getResult(sid);
        return result;
    }

    public static String getUserTaskImg(String taskId,String userId){
        int i = sendObject(1028,taskId,userId);
        return ResultPoor.getResult(i);
    }

    public static String getUserTaskNumOptimization(String id){
        int i = sendObject(1029,id);
        return ResultPoor.getResult(i);
    }

}
