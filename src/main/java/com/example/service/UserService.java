package com.example.service;

import com.example.pojo.User;

import java.util.List;

/**
 * 用户service接口
 * @author yxy
 */
public interface UserService {
    //验证登录
    User login(User user);
    /**
     * 用户管理功能-qym-查询所有用户
     * @return
     */
    List<User> getAll(Integer start,Integer limit);

    /**
     * 根据条件分页获取用户
     * @param user
     * @param start
     * @param limit
     * @return
     */
    List<User> getByCondition(User user,Integer start,Integer limit);

    /**
     * 根据条件获取用户总数
     * @param user
     * @return
     */
    int getCountByCondition(User user);
    /**
     * 获取用户总数
     * @return
     */
    int getCount();
    /**
     * 根据id查询某一用户信息
     */
    User selectById(Integer id);
    /**
     *根据id删除用户
     * @param id
     * @return
     */
    int deleteById(Integer id);
    //注册
    int insert(User user);

    //可选项注册
    int insertSelective(User user);

    //可选项更新user
    int updateByPrimaryKeySelective(User user);

    //检查用户名是否存在
    User selectByUsername(String username);
    //根据用户名查询用户id
    int getUIdByName(String userName);
}
