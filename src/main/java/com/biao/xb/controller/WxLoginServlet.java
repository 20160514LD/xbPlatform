package com.biao.xb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biao.xb.entity.User;
import com.biao.xb.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

@WebServlet("/wx_login")
public class WxLoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Properties prop = new Properties();
            InputStream in = UserServlet.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(in);

            String appid = prop.getProperty("wx.AppID");
            //微信授权成功后的回调地址
            String appSecret = prop.getProperty("wx.AppSecret");
            String code = request.getParameter("code");

            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                   "appid="+appid +

                   "&secret="+appSecret +
                   "&code="+code +
                   "&grant_type=authorization_code";

            //根据 code 请求 access_token
            HashMap<String,String> auth = execute(url);
            String access_token = auth.get("access_token");

            url = "https://api.weixin.qq.com/sns/userinfo?" +
                    "access_token="+access_token +
                    "&openid="+auth.get("openid");

            HashMap<String,Object> userInfo = execute(url);

            User user = userService.findByWxOpenId(auth.get("openid"));

            if (user == null) {
                //代表第一次使用微信扫码登录
                user = new User();
                //用户的头像
                user.setPic(userInfo.get("headimgurl").toString());
                //性别
                user.setGender(userInfo.get("sex").toString());
                //用户的昵称
                user.setRealName(userInfo.get("nickname").toString());
                user.setUsername(UUID.randomUUID().toString().substring(36 - 15).replace("-",""));
                //设置微信的 openid
                user.setWxOpenid(userInfo.get("openid").toString());

                //注册一个用户
                userService.register(user);

                //重新查询新的用户
                user = userService.findByWxOpenId(auth.get("openid"));
            }
            //更改用户登录时间
            userService.updateLoginTime(user.getId());

            request.getSession().setAttribute("loginUser",user);
            response.sendRedirect("/html/home.jsp");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行 http 请求
     * @param url
     * @return
     */
    public HashMap execute(String url) {
        try {
            //创建 HttpClient 实例
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            CloseableHttpResponse response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();

            String jsonStr = EntityUtils.toString(entity, "UTF-8");
            return JSONObject.parseObject(jsonStr,HashMap.class);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

















