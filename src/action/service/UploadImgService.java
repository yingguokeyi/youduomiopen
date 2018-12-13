package action.service;

import cache.ResultPoor;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 18330 on 2018/12/10.
 */
public class UploadImgService extends BaseService {

    //添加图片
    public static String addImg(String imageName, String imagePath,String imageThumbnailPath,String imageSamllPath,String imageMediumPath,String imageLargePath ) {
        int addSid = sendObjectCreate(674,imageName,imagePath,imageThumbnailPath,imageSamllPath,imageMediumPath,imageLargePath,"","");
        String result = ResultPoor.getResult(addSid);
        return result;
    }
}
