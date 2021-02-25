package com.biao.xb.dao;

import java.util.List;
import java.util.Map;

public class HomeDao extends BaseDao {


    /**
     * 查询 今日新注册用户、新发布文章、新发布会议等
     * @return
     */
    public Map<String, Object> findHomeCount() {
        try {
            String sql = "select\n" +
                    "\t(select count(1) from user where STR_TO_DATE(register_time,'%Y-%m-%d') = CURDATE()) userCount,\n" +
                    "\t(select count(1) from article where STR_TO_DATE(publish_date,'%Y-%m-%d') = CURDATE()) articleCount,\n" +
                    "\t(select count(1) from meeting where STR_TO_DATE(publish_date,'%Y-%m-%d') = CURDATE()) meetingCount";
            return jdbcTemplate.queryForMap(sql);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询近 7 日新注册用户、新发布文章、新发布会议
     * @return
     */
    public List<Map<String, Object>> findHomeDetail() {
        try {
            String sql = "\tselect \n" +
                    "\t( select count(1) from user where STR_TO_DATE(register_time,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-6) ) day6,\n" +
                    "\t( select count(1) from user where STR_TO_DATE(register_time,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-5) ) day5,\n" +
                    "\t( select count(1) from user where STR_TO_DATE(register_time,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-4) ) day4,\n" +
                    "\t( select count(1) from user where STR_TO_DATE(register_time,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-3) ) day3,\n" +
                    "\t( select count(1) from user where STR_TO_DATE(register_time,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-2) ) day2,\n" +
                    "\t( select count(1) from user where STR_TO_DATE(register_time,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-1) ) day1,\n" +
                    "\t( select count(1) from user where STR_TO_DATE(register_time,\"%Y-%m-%d\") = CURDATE() ) today\n" +
                    "\t\n" +
                    "\tUNION ALL\n" +
                    "\tselect \n" +
                    "\t( select count(1) from article where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-6) ) day6,\n" +
                    "\t( select count(1) from article where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-5) ) day5,\n" +
                    "\t( select count(1) from article where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-4) ) day4,\n" +
                    "\t( select count(1) from article where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-3) ) day3,\n" +
                    "\t( select count(1) from article where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-2) ) day2,\n" +
                    "\t( select count(1) from article where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-1) ) day1,\n" +
                    "\t( select count(1) from article where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = CURDATE() ) today\n" +
                    "\n" +
                    "UNION ALL\n" +
                    "\tselect \n" +
                    "\t( select count(1) from meeting where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-6) ) day6,\n" +
                    "\t( select count(1) from meeting where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-5) ) day5,\n" +
                    "\t( select count(1) from meeting where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-4) ) day4,\n" +
                    "\t( select count(1) from meeting where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-3) ) day3,\n" +
                    "\t( select count(1) from meeting where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-2) ) day2,\n" +
                    "\t( select count(1) from meeting where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = ADDDATE(CURDATE(),-1) ) day1,\n" +
                    "\t( select count(1) from meeting where STR_TO_DATE(publish_date,\"%Y-%m-%d\") = CURDATE() ) today";
            return jdbcTemplate.queryForList(sql);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
