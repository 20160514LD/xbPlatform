package com.biao.xb.controller;

import com.biao.xb.service.HomeService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/home/*")
public class HomeServlet extends BaseServlet {

    private HomeService homeService = new HomeService();

    /**
     * 首页信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询 今日注册用户、新发布文章、新发布会议等
        Map<String,Object> countData = homeService.findHomeCount();
        //查询近 7 日新注册用户、新发布文章、新发布会议
        List<Map<String,Object>> detailData = homeService.findHomeDetail();

        //转换为集合方式便于前端输出
        List<List> countList = new ArrayList<>();
        for (Map<String,Object> map : detailData) {
            List temp = new ArrayList();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                temp.add(entry.getValue());
            }
            countList.add(temp);

        }
//        Map<String,Object> data = new HashMap<>();
//        data.put("homeCount",countData);
//        data.put("detail",countList);
//
//        writeObjectToString(response,data);

        request.setAttribute("homeCount",countData);
        request.setAttribute("detail",countList);

        request.getRequestDispatcher("/html/home.jsp").forward(request,response);

    }
}
