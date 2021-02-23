package com.biao.xb.controller;

import com.biao.xb.convert.DateConverter;
import com.biao.xb.entity.Meeting;
import com.biao.xb.entity.PageEntity;
import com.biao.xb.service.MeetingService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {

    private MeetingService meetingService = new MeetingService();

    /**
     * 会议搜索 + 分页
     * @param request
     * @param response
     * @throws Exception
     */
    public void findPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        /**
         * 获取标题 状态  当前页
         */
        Map<String, String> param = getParam(request);
        String title = param.get("title");

        Integer currPage = Integer.parseInt(param.get("currPage"));
        Integer status = -1;
        if (!"".equals(param.get("status"))) {
            status = Integer.parseInt(param.get("status"));
        }

        PageEntity<Meeting> pageData= meetingService.findPage(title,status,currPage);

        request.setAttribute("pageData",pageData);
        request.setAttribute("status",status);
        request.setAttribute("title",title);

        request.getRequestDispatcher("/html/meeting.jsp").forward(request, response);
    }

    /**
     * 发布会议
     * @param request
     * @param response
     * @throws Exception
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        Meeting meeting = new Meeting();

        //类型转化工具类
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        /**
         * 注册日期转换器，负责转换 Date 类型
         * 参数1：处理抓换逻辑的核心类（必须是 Converter的子类）
         * 参数2：最终要转换的类型
         */
        DateConverter dateConverter = new DateConverter();
        convertUtilsBean.register(dateConverter, Date.class);
        //bean 对象映射工具类
        BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtilsBean);
        //使用了注册类型转换器工具的映射工具类机进行属性映射
        try {
            beanUtils.populate(meeting,param);
        }catch (Exception e) {
            e.printStackTrace();
        }

        meetingService.save(meeting);

        response.sendRedirect("/meeting/findPage?currPage=1&status=-1");
    }

    /**
     * 会议详情
     * @param request
     * @param response
     * @throws Exception
     */
    public void meetingDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        /**
         *  前端传过来 会议id
         *  根据 makeUser 字段截取 读取 字符串长度即应到人数
         *  实到人数：根据 meeting_join 中查询
         *  是否能操作本次会议： 查询 meeting_join 中是否存在记录
         */
        Map<String, String> param = getParam(request);
        long id = Long.parseLong(param.get("id"));

        //查询 Meeting 对象
        Meeting meeting = meetingService.findById(id);

        //获取 meeting 对象的 makeUser 字段
        String makeUser = meeting.getMakeUser();
        String[] joinUser = makeUser.split(",");

        // 根据 meeting_join 中查询记录数
        Integer realCount = meetingService.countJoinMeetingByMeetingId(id);
        //未到人数
        Integer noCount = joinUser.length - realCount;

        // 是否能操作本次会议 meeting_join 中查询是否存在记录
        Boolean flag = meetingService.isJoinMeeting(loginUser.getId(), id);
        Boolean isJoin = Arrays.asList(joinUser).contains(loginUser.getId()+"");
        request.setAttribute("meeting", meeting);
        request.setAttribute("joinCount", joinUser.length);
        request.setAttribute("realCount", realCount);
        request.setAttribute("noCount", noCount);
        request.setAttribute("flag", flag);
        // 当前登录用户是否可以参加此次会议
        request.setAttribute("isJoin", isJoin);

        request.getRequestDispatcher("/html/meeting_detail.jsp").forward(request,response);
    }


    /**
     * 参加 / 取消 会议
     * @param request
     * @param response
     * @throws Exception
     */
    public void joinMeeting(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> param = getParam(request);
        long id = Long.parseLong(param.get("id"));

        //查询原来是否有参加过这个会议
        Boolean joinMeeting = meetingService.isJoinMeeting(loginUser.getId(), id);

        if (joinMeeting) {
            //说明原来参加过这个会议，取消会议
            meetingService.unJoinMeeting(loginUser.getId(), id);
        }else {
            //说明没有参加过这个会议，参加会议
            meetingService.joinMeeting(loginUser.getId(), id);
        }
        // 重定向到/meeting/meetingDetail请求
        response.sendRedirect("/meeting/meetingDetail?id="+id);
    }

}














