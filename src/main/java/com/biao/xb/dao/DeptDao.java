package com.biao.xb.dao;


import com.biao.xb.entity.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Map;

public class DeptDao extends BaseDao {

    /**
     * 查询部门详细信息
     * @return
     */
    public List<Map<String, Object>> findAllMap() {
        try {
            String sql="SELECT\n" +
                    "\td.*,\n" +
                    "\tcount( u.dept_id ) deptCount \n" +
                    "FROM\n" +
                    "\tdept d\n" +
                    "\tLEFT JOIN USER u ON d.id = u.dept_id \n" +
                    "GROUP BY\n" +
                    "\td.id";
            return jdbcTemplate.queryForList(sql);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询所有部门
     * @return
     */
    public List<Dept> finAll() {
        try {
            return jdbcTemplate.query("select * from dept",new BeanPropertyRowMapper<>(Dept.class));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据部门id 查询部门
     * @param deptId
     * @return
     */
    public Dept findById(Long deptId) {
        try {
            return jdbcTemplate.queryForObject("select * from dept where id = ?",new BeanPropertyRowMapper<>(Dept.class),deptId);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
