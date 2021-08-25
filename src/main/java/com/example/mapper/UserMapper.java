package com.example.mapper;

import com.example.pojo.User;


public interface UserMapper {
    //验证登录
    User login(User user);

    //注册
    int insert(User user);

    //可选项注册
    int insertSelective(User user);

    //可选项更新user
    int updateByPrimaryKeySelective(User user);

    //检查用户名是否存在
    User selectByUsername(String username);



}