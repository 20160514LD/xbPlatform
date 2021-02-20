package com.biao.xb.entity;

import java.util.List;

/*
分页实体类
 */
public class PageEntity<T> {

    //当前页数据
    private List<T> data;

    //页大小
    private Integer pageSize = 5;

    //总记录数
    private Integer totalCount;

    //总页数
    private Integer totalPage;

    //当前页
    private Integer currPage;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "data=" + data +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currPage=" + currPage +
                '}';
    }
}
