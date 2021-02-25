package com.biao.xb.filter;

import com.biao.xb.entity.User;
import com.biao.xb.service.UserService;
import com.biao.xb.utils.XBUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    private UserService userService = new UserService();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //统一处理乱码
        request.setCharacterEncoding("utf-8");

        //获取请求 URI
        String uri = request.getRequestURI();

        String targetName = uri.substring(uri.lastIndexOf("/")+1);

        if (
                uri.startsWith("/bootstrap") ||         //bootstrap 资源路径下的请求全部放行
                        uri.startsWith("/css") ||
                        uri.startsWith("/fonts") ||
                        uri.startsWith("/img") ||
                        uri.startsWith("/js") ||
                        uri.startsWith("/upload") ||
                        uri.startsWith("/vendor") ||


                        targetName.equals("checkCode.jsp") ||       //放行checkCode.jsp页面
                        targetName.equals("forget.jsp") ||
                        targetName.equals("index.jsp") ||
                        targetName.equals("taglib.jsp") ||
                        targetName.equals("register.jsp") ||


                        targetName.equals("login") ||                  // 放行登录请求
                        targetName.equals("wx_login") ||
                        targetName.equals("logout") ||
                        targetName.equals("forget") ||
                        targetName.equals("register") ||
                        targetName.equals("sendEmail") ||
                        targetName.equals("checkUsername") ||
                        targetName.equals("checkEmail")
        ) {
            //放行资源
            filterChain.doFilter(request,response);
            //加 return ,不然请求放行了代码还是会往下执行
            return;
        }
        //判断 session 域中的资源
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("loginUser");
        if (loginUser != null) {
            filterChain.doFilter(request,response);
            return;
        }

        //获取请求头上 loginUser 的 Cookie 信息
        Cookie loginCookie = XBUtils.getCookie(request, "loginUser");

        if (loginCookie != null) {
            //说明请求头上的 cookie 是有携带登录信息的
            //准备读取的 cookie 中的 value
            long userId = Long.parseLong(loginCookie.getValue());
            session.setAttribute("loginUser",userService.findById(userId));
            filterChain.doFilter(request,response);
            return;
        }

        //重定向到登录页面
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    @Override
    public void destroy() {

    }
}
