package common;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 * @ClassName FileFactory
 * @Description TODO
 * @Author yanhuo
 * @Date 2018/11/21 13:40
 * @Version 1.0
 **/
public class FileFactory {
    static DiskFileItemFactory factory = new DiskFileItemFactory();

    public static DiskFileItemFactory getFactory(){
        return factory;
    }
}
