package com.biao.xb.controller;

import com.biao.xb.entity.User;
import com.biao.xb.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserService();

    /**
     * 检查 邮箱是否可用
     * @param request
     * @param response
     * @throws Exception
     */
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        User user = userService.findByEmail(email);
        response.setContentType("application/json;charset=utf-8");
        if (user == null) {
            response.getWriter().write("0");
            return;
        }
        response.getWriter().write("1");
    }

    /**
     * 检查 用户名 是否可用
     * @param request
     * @param response
     * @throws Exception
     */
    public void checkUsername(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");

        User user = userService.findByUsername(username);
        response.setContentType("application/json;charset=utf-8");
        if (user == null) {
            response.getWriter().write("0");
            return;
        }
        response.getWriter().write("1");
    }

    /**
     * 注册操作
     * @param request
     * @param response
     * @throws Exception
     */
    public void register(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        User user = new User();
        BeanUtils.populate(user,param);

        userService.register(user);

        response.sendRedirect("/index.jsp");
    }

    /**
     * 登录操作
     * @param request
     * @param response
     * @throws Exception
     */
    public void login(HttpServletRequest request,HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        // 1.接收参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        // 2.校验验证码是否一致
        String sessionCode = (String) session.getAttribute("sessionCode");

        if (!checkCode.equals(sessionCode)) {
            // 验证码错误
            request.setAttribute("error", "验证码错误");
            request.getRequestDispatcher("/index.jsp").forward(request, response);

            // 结束方法的执行
            return;
        }
        // 3.判断用户名是否是存在并且密码是否正确
        User user = userService.findByUsername(username);
        if (!username.equals(user.getUsername())) {
            // 用户名不存在(用户名错误)
            request.setAttribute("error", "用户名不存在");
            request.getRequestDispatcher("/index.jsp").forward(request, response);

            // 结束方法的执行
            return;
        }

        if (!password.equals(user.getPassword())) {
            // 用户名不存在(用户名错误)
            request.setAttribute("error", "密码错误");
            request.getRequestDispatcher("/index.jsp").forward(request, response);

            // 结束方法的执行
            return;
        }

        // 保存到session中,方便下次取出
        session.setAttribute("loginUser",user);
        // 重定向到首页
        response.sendRedirect("/html/home.jsp");
    }
}
