package com.biao.xb.service;

import com.biao.xb.dao.MeetingDao;
import com.biao.xb.entity.Meeting;
import com.biao.xb.entity.PageEntity;

import java.util.Date;
import java.util.List;

public class MeetingService {

    private MeetingDao meetingDao = new MeetingDao();

    /**
     * 会议搜索 + 分页
     * @param title
     * @param status
     * @param currPage
     * @return
     */
    public PageEntity<Meeting> findPage(String title, Integer status, Integer currPage) {
        PageEntity<Meeting> pageEntity = new PageEntity<>();
        Integer pageSize = pageEntity.getPageSize();
        //起始索引
        Integer startIndex = (currPage - 1) * pageSize;
        //查询总结果
        List<Meeting> meetings =  meetingDao.findPage(title,status,startIndex,pageSize);
        //查询总记录数
        Integer totalCount = meetingDao.countByTitleAndStatus(title, status);
        //计算总页数
        Integer totalPage = totalCount % pageSize  == 0? totalCount / pageSize : totalCount / pageSize + 1;

        pageEntity.setCurrPage(currPage);
        pageEntity.setTotalPage(totalPage);
        pageEntity.setTotalCount(totalCount);
        pageEntity.setData(meetings);

        return pageEntity;
    }

    /**
     * 发布会议
     * @param meeting
     */
    public void save(Meeting meeting) {
        meeting.setStatus(0L);
        meeting.setPublishDate(new Date());
        meetingDao.save(meeting);
    }

    /**
     * 根据会议id 查询 Meeting 对象
     * @param id
     * @return
     */
    public Meeting findById(long id) {
        return meetingDao.findById(id);
    }

    /**
     * 根据 meeting_join 中查询记录数
     * @param id
     * @return
     */
    public Integer countJoinMeetingByMeetingId(long id) {
        return meetingDao.countJoinMeetingByMeetingId(id);
    }

    /**
     * 是否能操作本次会议 meeting_join 中查询是否存在记录
     * @param userId
     * @param meetingId
     * @return
     */
    public Boolean isJoinMeeting(Long userId, long meetingId) {
        Integer count = meetingDao.countByUserIdAndMeetingId(userId, meetingId);
        return count == 0? false:true;
    }

    /**
     * 说明原来参加过这个会议，取消会议
     * @param userId
     * @param meetingId
     */
    public void unJoinMeeting(Long userId, long meetingId) {
        meetingDao.unJoinMeeting(userId,meetingId);
    }

    /**
     * 说明没有参加过这个会议，参加会议
     * @param userId
     * @param meetingId
     */
    public void joinMeeting(Long userId, long meetingId) {
        meetingDao.joinMeeting(userId,meetingId);
    }
}
