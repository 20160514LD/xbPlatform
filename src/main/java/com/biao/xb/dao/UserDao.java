package com.biao.xb.dao;

import com.biao.xb.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询所有用户
     * @return
     */
    public List<User> findAll() {
        //使用 BeanPropertyRowMapper 自动映射
        return jdbcTemplate.query("select * from user",new BeanPropertyRowMapper<>(User.class));
    }


    /**
     * 根据条件查询所有用户
     * @param realName
     * @return
     */
    public List<User> search(String realName) {
        return jdbcTemplate.query("select * from user where real_name like ?",new BeanPropertyRowMapper<>(User.class),"%"+realName+"%");
    }

    /**
     * 根据用户条件查询分页
     * @param realName
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<User> findPage(String realName, Integer startIndex, Integer pageSize) {
        try {
            return jdbcTemplate.query("select * from user where real_name like ? limit ?,?",new BeanPropertyRowMapper<>(User.class),"%"+realName+"%",startIndex,pageSize);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据条件查询总记录数
     * @param realName
     * @return
     */
    public Integer countByRealName(String realName) {
        try {
            return jdbcTemplate.queryForObject("select count(1) from user where real_name like ? ",Integer.class,"%"+realName+"%");
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据部门查询部门下的用户
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> findUserByDeptId(long deptId) {
        try {
            return jdbcTemplate.queryForList("select id,real_name realName,is_secret isSecret from user where dept_id=?",deptId);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据用户id 查询用户
     * @param id
     * @return
     */
    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from user where id = ?",new BeanPropertyRowMapper<>(User.class),id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询粉丝数
     * @param id
     * @return
     */
    public Integer countFansByUserId(Long id) {
        try {
            return jdbcTemplate.queryForObject("select count(1) from userfocus where user_focus_id = ?",Integer.class,id);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 被看数 + 1
     * @param id
     */
    public void incLook(Long id) {
        jdbcTemplate.update("update user set look = look + 1 where id=?",id);
    }

    /**
     * 根据id查询关注数
     * @param id
     * @return
     */
    public Integer countFocusByUserId(Long id) {
        try {
            return jdbcTemplate.queryForObject("select count(*) from userfocus where user_id=?",
                    Integer.class, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 更新用户的图片地址
     * @param id
     * @param picUrl
     */
    public void update(Long id, String picUrl) {
        jdbcTemplate.update("update user set pic=? where id = ?",picUrl,id);
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUserInfo(User user) {
        jdbcTemplate.update(
                "update user set password=?,real_name=?,age=?,phone=?,gender=?,info=?,is_secret=?,dept_name=?,dept_id=? where id=?",
                user.getPassword(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getGender(),
                user.getInfo(),
                user.getIsSecret(),
                user.getDeptName(),
                user.getDeptId(),
                user.getId());
    }

    /**
     * 根据用户id查询关注数列表
     * @param id
     * @return
     */
    public List<Long> findFocusListByUserId(Long id) {
        try {
            return jdbcTemplate.query("select user_focus_id from userfocus uf where uf.user_id=?", new ResultSetExtractor<List<Long>>() {
                @Override
                public List<Long> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    List<Long> list = new ArrayList<>();
                    while (resultSet.next()) {
                        list.add(resultSet.getLong("user_focus_id"));
                    }
                    return list;
                }
            },id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取消关注
     * @param id
     * @param userId
     */
    public void unFocus(Long id, Long userId) {
        jdbcTemplate.update("delete from userfocus where user_id=? and user_focus_id = ?",id,userId);
    }

    /**
     * 添加关注
     * @param id
     * @param userId
     */
    public void focus(Long id, Long userId) {
        jdbcTemplate.update("insert into userfocus values(?,?)",id,userId);
    }

    /**
     * 查询我关注的用户+分页
     * @param id
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> findFocusPage(Long id, Integer startIndex, Integer pageSize) {
        try {
            String sql="select u.id,u.real_name realName,u.is_secret isSecret from user u where u.id in \n" +
                    "    (\n" +
                    "        select user_focus_id from userfocus uf where user_id=?\n" +
                    "    )\n" +
                    "    limit ?,?";
            return jdbcTemplate.queryForList(sql,id,startIndex,pageSize);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}














