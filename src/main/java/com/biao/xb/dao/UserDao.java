package com.biao.xb.dao;

import com.biao.xb.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class UserDao extends BaseDao{

    /**
     * 检查 邮箱 是否可用
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject("select * from user where email=?",new BeanPropertyRowMapper<>(User.class),email);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 检查 用户名 是否可用
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        try {
            // 可能会查询不到
            return jdbcTemplate.queryForObject("select * from user where username=?", new BeanPropertyRowMapper<>(User.class), username);
        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
        }

        return null;
    }


    /**
     * 新增用户
     * @param user
     */
    public void save(User user) {
        jdbcTemplate.update("insert into user values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getQqOpenid(),
                user.getWxOpenid(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getGender(),
                user.getInfo(),
                user.getRegisterTime(),
                user.getLoginTime(),
                user.getPic(),
                user.getLook(),
                user.getIsSecret(),
                user.getDeptName(),
                user.getDeptId()
        );
    }
}














