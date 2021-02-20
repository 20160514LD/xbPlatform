package com.biao.xb.service;

import com.biao.xb.dao.UserDao;
import com.biao.xb.entity.PageEntity;
import com.biao.xb.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserService {
    private UserDao userDao = new UserDao();


    /**
     * 检查 邮箱 是否可用
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 检查 用户名 是否可用
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 用户注册
     * @param user
     */
    public void register(User user) {
        // 默认私密
        user.setIsSecret("0");
        // 真实姓名默认用户名
        user.setRealName(user.getUsername());
        // 注册时间为当前
        user.setRegisterTime(new Date());
        //默认头像
        user.setPic("http://localhost:8080/upload/def.png");

        userDao.save(user);
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 根据条件查询所有用户
     * @param realName
     * @return
     */
    public List<User> search(String realName) {
        return userDao.search(realName);
    }

    /**
     * 根据条件进行分页查询
     * @param realName
     * @param currPage
     * @return
     */
    public PageEntity<User> findPage(String realName, Integer currPage) {
        PageEntity<User> pageEntity = new PageEntity<>();
        Integer pageSize = pageEntity.getPageSize();
        Integer startIndex = (currPage - 1) * pageSize;

        //获取分页数据
        List<User> userList = userDao.findPage(realName,startIndex,pageSize);
        //获取总记录数
        Integer totalCount = userDao.countByRealName(realName);
        //获取分页总数
        Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        pageEntity.setCurrPage(currPage);
        pageEntity.setData(userList);
        pageEntity.setTotalCount(totalCount);
        pageEntity.setTotalPage(totalPage);

        return pageEntity;

    }

    /**
     * 根据部门查询部门中的用户
     * @param deptId
     * @return
     */
    public List<Map<String, Object>> findUserByDeptId(long deptId) {
        return userDao.findUserByDeptId(deptId);
    }

    /**
     * 根据用户id 查询用户信息
     * @param id
     * @return
     */
    public User findById(Long id) {
        return userDao.findById(id);
    }

    /**
     * 查询粉丝数
     * @param id
     * @return
     */
    public Integer countFansByUserId(Long id) {
        return userDao.countFansByUserId(id);
    }

    /**
     * 被看数 + 1
     * @param id
     */
    public void incLook(Long id) {
        userDao.incLook(id);
    }

    /**
     * 根据id查询关注数
     * @param id
     * @return
     */
    public Integer countFocusByUserId(Long id) {
        return userDao.countFocusByUserId(id);
    }

    /**
     * 更新用的图片地址
     * @param id
     * @param picUrl
     */
    public void update(Long id, String picUrl) {
        userDao.update(id,picUrl);
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUserInfo(User user) {
        userDao.updateUserInfo(user);
    }
}

















