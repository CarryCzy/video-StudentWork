package com.example.mapper;

import com.example.pojo.Admins;

import java.util.List;

public interface AdminsMapper {
    /**
     *管理员后台登录
     * @param admins
     * @return
     */
    Admins login(Admins admins);

    Admins selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Admins admins);

    int insertSelective(Admins admins);


    int updateByPrimaryKeySelective(Admins admins);

    int updateByPrimaryKey(Admins admins);
}