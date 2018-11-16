package action.service;

import cache.ResultPoor;

/**
 * Created by 18330 on 2018/11/14.
 */
public class MemerService extends BaseService {

    //查询所有下级会员
    public static String getAllLower(String useId) {
        int i = sendObject(950, useId,useId);
        return ResultPoor.getResult(i);
    }

    //查询下级会员
    public static String getLower(String useId) {
        int i = sendObject(951, useId);
        return ResultPoor.getResult(i);
    }

    //查询下下级会员
    public static String getLowerLower(String useId) {
        int i = sendObject(952, useId);
        return ResultPoor.getResult(i);
    }

    //查询下级会员人数
    public static String getLowerCount(String useId) {
        int i = sendObject(953, useId);
        return ResultPoor.getResult(i);
    }

    //查询下下级会员人数
    public static String getLowerLowerCount(String useId) {
        int i = sendObject(954, useId);
        return ResultPoor.getResult(i);
    }

    //查询会员详情
    public static String getLowerUserInfo(String useId) {
        int i = sendObject(955, useId,useId,useId);
        return ResultPoor.getResult(i);
    }

}
