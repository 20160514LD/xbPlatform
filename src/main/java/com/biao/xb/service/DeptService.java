package com.biao.xb.service;

import com.biao.xb.dao.DeptDao;
import com.biao.xb.entity.Dept;

import java.util.List;
import java.util.Map;

public class DeptService {

    private DeptDao deptDao = new DeptDao();


    /**
     * 查询部门的详细数据
     * @return
     */
    public List<Map<String, Object>> findAllMap() {
        return deptDao.findAllMap();
    }

    /**
     * 查询所有的部门
     * @return
     */
    public List<Dept> findAll() {
        return deptDao.finAll();
    }

    /**
     * 根据部门id查询部门信息
     * @param deptId
     * @return
     */
    public Dept findById(Long deptId) {
        return deptDao.findById(deptId);
    }
}
