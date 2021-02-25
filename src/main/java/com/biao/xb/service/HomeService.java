package com.biao.xb.service;

import com.biao.xb.dao.HomeDao;

import java.util.List;
import java.util.Map;

public class HomeService {

    private HomeDao homeDao = new HomeDao();


    /**
     * 查询 今日新注册用户、新发布文章、新发布会议等
     * @return
     */
    public Map<String, Object> findHomeCount() {
        return homeDao.findHomeCount();
    }

    /**
     * 查询近 7 日新注册用户、新发布文章、新发布会议
     * @return
     */
    public List<Map<String, Object>> findHomeDetail() {
        return homeDao.findHomeDetail();
    }
}
