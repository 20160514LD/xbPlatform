package com.biao.xb.entity;

import java.util.Date;

public class Meeting {
    private Long id;
    private String deptName;
    private Long deptId;
    private String title;
    private String content;
    private Date publishDate;
    private Date startTime;
    private Date endTime;
    private Long status;
    private String makeUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getMakeUser() {
        if (makeUser.length() == 1) {
            return makeUser;
        }
        makeUser = makeUser.substring(1);
        makeUser = makeUser.substring(0,makeUser.length() - 1);
        return makeUser;
    }

    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", makeUser='" + makeUser + '\'' +
                '}';
    }
}
