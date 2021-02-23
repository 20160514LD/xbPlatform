package com.biao.xb.controller;

import com.biao.xb.entity.Dept;
import com.biao.xb.service.DeptService;
import com.biao.xb.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {
    private DeptService deptService = new DeptService();
    private UserService userService = new UserService();

    /**
     * 查询部门及相关人员
     * @param request
     * @param response
     * @throws Exception
     */
    public void findAllMap(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //查询部门的详细信息
        List<Map<String,Object>> deptMapList = deptService.findAllMap();

        for (Map<String,Object> dept : deptMapList) {
            long deptId = Long.parseLong(dept.get("id").toString());

            List<Map<String,Object>> userMapList = userService.findUserByDeptId(deptId);

            dept.put("userMapList",userMapList);
        }

        request.setAttribute("deptMapList",deptMapList);
        request.getRequestDispatcher("/html/department.jsp").forward(request,response);

    }

    /**
     * 查询部门的信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Dept> depts = deptService.findAll();
        writeObjectToString(response,depts);
    }

}
