package com.biao.xb.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

public class FileUploadUtils {

    /**
     *  文件上传工具
     * @param realPath: 上传到服务器的路径
     * @param request
     * @return: 返回图片的名称（随机生成）
     */
    public static String upload(String realPath, HttpServletRequest request) {
        //为解析类提供配置信息，创建文件上床工具类
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //创建解析类的实例，传入工厂获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);

        //设置文件最大解析大小
        sfu.setFileSizeMax(1024 * 400);
        //每个表单域中数据会封装到一个对应的 FileItem 对象中
        try {
            List<FileItem> items = null;
            items = sfu.parseRequest(request);

            String fileName = "";
            //区分表单域
            for (int i=0;i< items.size();i++) {
                FileItem item = items.get(i);
                //isFormField 为 true ，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    //文件上传的名字
                    String name = item.getName();
                    //获取文件名后缀
                    String suffix = name.substring(name.lastIndexOf("."));
                    //获得文件名
                    fileName = UUID.randomUUID().toString() + suffix;
                    //获取 upload 在电脑的真实路径
                    String path = request.getRealPath(realPath);

                    File file = new File(path + "/" + fileName);
                    if (!file.exists()) {
                        //将文件写出到指定盘
                        item.write(file);
                    }
                    return fileName;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
