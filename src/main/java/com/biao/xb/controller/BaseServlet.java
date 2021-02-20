package com.biao.xb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 统一处理Post提交乱码问题
        request.setCharacterEncoding("utf-8");

        String uri = request.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/") + 1);

        try {
            Class<? extends BaseServlet> target = this.getClass();
            Method method = target.getMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 request 中的键值队，返回 Map<String,String>
     * @param request
     * @return
     */
    public static Map<String,String> getParam(HttpServletRequest request) {
        Map<String, String> returnMap = new HashMap<>();

        //request 中封装的前端的值
        Map<String,String[]> parameterMap = request.getParameterMap();

        for (String key : parameterMap.keySet()) {
            String[] values = parameterMap.get(key);
            //提交了多值
            if (values.length > 1) {
                //使用逗号拼接字符串
                String value = Arrays.toString(values);
                returnMap.put(key,value);
            }else {
                //只要第0个下标的值
                returnMap.put(key,values[0]);
            }
        }
        return returnMap;
    }

    /**
     * 将对象转成字符串写出（处理 ajax请求）
     * @param response
     * @param obj
     */
    public void writeObjectToString(HttpServletResponse response,Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonStr = objectMapper.writeValueAsString(obj);
            //统一设置编码
            response.setContentType("application/json'charset=utf-8");
            response.getWriter().write(jsonStr);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}























