package com.biao.xb.dao;

import com.biao.xb.entity.Meeting;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class MeetingDao extends BaseDao {


    /**
     * 会议搜索 + 分页
     * @param title
     * @param status
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<Meeting> findPage(String title, Integer status, Integer startIndex, Integer pageSize) {
        /**
         * select * from meeting where title like ? and status = ? order by publish_date limit ?,?
         */
        Object[] args = null;
        String sql = "";
        if (status == -1) {
            sql = "select * from meeting where title like ? order by publish_date desc limit ?,?";
            args = new Object[]{"%"+title+"%",startIndex,pageSize};
        }else {
            sql = "select * from meeting where title like ? and status = ? order by publish_date desc limit ?,?";
            args = new Object[]{"%"+title+"%",status,startIndex,pageSize};
        }
        try {
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Meeting.class),args);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param title
     * @param status
     * @return
     */
    public Integer countByTitleAndStatus(String title, Integer status) {
        Object[] args = null;
        String sql = "";
        if (status == -1) {
            sql = "select count(1) from meeting where title like ? ";
            args = new Object[]{"%"+title+"%"};
        }else {
            sql = "select count(1) from meeting where title like ? and status = ? ";
            args = new Object[]{"%"+title+"%",status};
        }
        try {
            return jdbcTemplate.queryForObject(sql,Integer.class,args);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发布会议
     * @param meeting
     */
    public void save(Meeting meeting) {
        jdbcTemplate.update("insert into meeting values(null,?,?,?,?,?,?,?,?,?)",
                meeting.getDeptName(),
                meeting.getDeptId(),
                meeting.getTitle(),
                meeting.getContent(),
                meeting.getPublishDate(),
                meeting.getStartTime(),
                meeting.getEndTime(),
                meeting.getStatus(),
                "[" + meeting.getMakeUser() + "]"
        );
    }

    /**
     * 根据会议id 查询 Meeting 对象
     * @param id
     * @return
     */
    public Meeting findById(long id) {
        try {
            return jdbcTemplate.queryForObject("select * from meeting where id = ?",new BeanPropertyRowMapper<>(Meeting.class),id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据 meeting_join 中查询记录数
     * @param id
     * @return
     */
    public Integer countJoinMeetingByMeetingId(long id) {
        try {
            return jdbcTemplate.queryForObject("select count(1) from meeting_join where c_id = ?",Integer.class,id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否能操作本次会议 meeting_join 中查询是否存在记录
     * @param userId
     * @param meetingId
     * @return
     */
    public Integer countByUserIdAndMeetingId(Long userId, long meetingId) {
        try {
            return jdbcTemplate.queryForObject("select count(1) from meeting_join where u_id = ? and c_id = ?",Integer.class,userId,meetingId);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 说明原来参加过这个会议，取消会议
     * @param userId
     * @param meetingId
     */
    public void unJoinMeeting(Long userId, long meetingId) {
        try {
            jdbcTemplate.update("delete from meeting_join where u_id=? and c_id=?",userId,meetingId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 说明没有参加过这个会议，参加会议
     * @param userId
     * @param meetingId
     */
    public void joinMeeting(Long userId, long meetingId) {
        try {
            jdbcTemplate.update("insert into meeting_join values(?,?) ",userId,meetingId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
