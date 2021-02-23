package com.biao.xb.controller;

import com.biao.xb.entity.Dept;
import com.biao.xb.entity.PageEntity;
import com.biao.xb.entity.User;
import com.biao.xb.service.DeptService;
import com.biao.xb.service.UserService;
import com.biao.xb.utils.FileUploadUtils;
import com.biao.xb.utils.XBUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserService();
    private DeptService deptService = new DeptService();

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
        String remember = request.getParameter("remember");

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

        //更改用户登录时间
        userService.updateLoginTime(user.getId());

        if ("1".equals(remember)) {
            //勾选了
            Cookie cookie = new Cookie("loginUser",user.getId()+"");
            //设置 cookie 携带路径（整体项目都携带）
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);

        }
        // 保存到session中,方便下次取出
        session.setAttribute("loginUser",user);
        // 重定向到首页
        response.sendRedirect("/html/home.jsp");
    }


    /**
     * 查询所有用户
     * @param request
     * @param response
     * @throws Exception
     */
    public void findAll(HttpServletRequest request,HttpServletResponse response) throws Exception{

        String realName = getParam(request).get("realName");

        //返回所有的用户
       // List<User> userList = userService.findAll();
        List<User> userList = userService.search(realName);

        request.setAttribute("realName",realName);
        request.setAttribute("userList",userList);

        request.getRequestDispatcher("/html/user.jsp").forward(request,response);
    }


    /**
     * 用户搜索 + 分页
     * @param request
     * @param response
     * @throws Exception
     */
    public void findPage(HttpServletRequest request,HttpServletResponse response) throws Exception {

        Map<String, String> param = getParam(request);

        String realName = param.get("realName");

        //当前页码
        Integer currPage = Integer.parseInt(param.get("currPage"));

        //查询分页中的实体
        PageEntity<User> pageEntity = userService.findPage(realName,currPage);
        //判断哪些用户是我所关注的
        List<Long> focusList = userService.findFocusListByUserId(loginUser.getId());

        request.setAttribute("focusList",focusList);

        //返回数据给前端
        request.setAttribute("pageData",pageEntity);
        request.setAttribute("realName",realName);

        //跳转页面
        request.getRequestDispatcher("/html/user.jsp").forward(request,response);
    }

    /**
     * 跳转用户详细页面
     * @param request
     * @param response
     * @throws Exception
     */
    public void userDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        Long id = Long.parseLong(param.get("id"));

        //获取前端传过来的标识符 flag
        String flag = param.get("flag");
        User user = userService.findById(id);
        request.setAttribute("user",user);

        //判断是查看自己 还是 从查看别人
        if ("detail".equals(flag)) {    //查看别人的首页

            //查询粉丝数
            Integer fansCount = userService.countFansByUserId(id);
            //查询别看数 + 1
            userService.incLook(id);
            //查询用户实体信息

            request.setAttribute("fans",fansCount);
            request.getRequestDispatcher("/html/user_detail.jsp").forward(request,response);
            return;
        }

            //查询关注数
            Integer focusCount = userService.countFocusByUserId(id);
            //查询部门信息
            List<Dept> deptList = deptService.findAll();
            //查询用户的信息
            request.setAttribute("focus", focusCount);
            request.setAttribute("deptList",deptList);

            request.getRequestDispatcher("/html/user_look.jsp").forward(request, response);

    }

    public void updatePic(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //调用文件 上传工具
        String fileName = FileUploadUtils.upload("/upload",request);

        //上传服务器端的真是路径
        String picUrl = "http://localhost:8080/upload/" + fileName;
        User loginUser = (User)request.getSession().getAttribute("loginUser");

        //修改 session 中的 pic地址
        loginUser.setPic(picUrl);
        request.getSession().setAttribute("loginUser",loginUser);

        //修改数据库中的 pic 地址
        userService.update(loginUser.getId(),picUrl);

        //将新的图片地址写回前端
        writeObjectToString(response,picUrl);
    }

    /**
     * 更新用户信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void update(HttpServletRequest request,HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");

        User user = new User();
        BeanUtils.populate(user,getParam(request));

        User dbUser = userService.findById(user.getId());

        //查询部门信息
        Dept dept = deptService.findById(user.getDeptId());

        user.setDeptName(dept.getName());

        //设置前端不能提交的值
        user.setPic(dbUser.getPic());
        user.setLook(dbUser.getLook());
        user.setPassword(dbUser.getPassword());
        user.setEmail(dbUser.getEmail());

        //前端没有勾选默认为公开
        if (user.getIsSecret() == null) {
            user.setIsSecret("1");  //公开
        }

        //更新用户信息
        userService.updateUserInfo(user);

        //重新放入 session
        request.getSession().setAttribute("loginUser",user);

        // 重新重定向到userDetail请求填充数据,然后通过userDetail转发到对应的页面进行数据渲染
        response.sendRedirect("/user/userDetail?flag=detail&id=" + dbUser.getId());
    }

    /**
     * 加关注/取消关注
      * @param request
     * @param response 1:表示添加用户关注 0：表示用户已经添加过关注（取消关注）
     * @throws Exception
     */
    public void focus(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Long userId = Long.parseLong(getParam(request).get("userId"));

        Long id = loginUser.getId();

        // 用户加关注(1:关注  0:取消关注)
        Integer flag = userService.focus(id,userId);

        writeObjectToString(response,flag);
    }

    /**
     * 查询我已经关注的用户 + 分页
     * @param request
     * @param response
     * @throws Exception
     */
    public void findFocusPage(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        //获取当前页
        Integer currPage = Integer.parseInt(param.get("currPage"));

        //根据条件进行分页查询
        PageEntity<Map<String, Object>> pageData = userService.findFocusPage(loginUser.getId(),currPage);
        //返回结果给前端
        request.setAttribute("pageData", pageData);

        request.getRequestDispatcher("/html/my_user.jsp").forward(request,response);

    }

    /**
     * 根据部门id查询用户信息
     * @param request
     * @param response
     * @throws Exception
     */
    public void findUserByDeptId(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        Long deptId = Long.parseLong(param.get("deptId"));

        List<Map<String, Object>> userByDeptId = userService.findUserByDeptId(deptId);

        writeObjectToString(response,userByDeptId);
    }


    /**
     * 注销
     * @param request
     * @param response
     * @throws Exception
     */
    public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
        session.invalidate();

        Cookie loginCookie = XBUtils.getCookie(request, "loginUser");
        if (loginUser != null) {
            // 销毁cookie
            loginCookie.setPath("/");
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }
        response.sendRedirect("/index.jsp");
    }
}























