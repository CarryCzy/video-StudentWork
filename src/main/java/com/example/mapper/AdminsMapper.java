package com.example.mapper;

import com.example.pojo.Admins;

public interface AdminsMapper {
    /**
     *管理员后台登录
     * @param admins
     * @return
     */
    Admins login(Admins admins);
    int deleteByPrimaryKey(Integer id);

    int insert(Admins admins);

    int insertSelective(Admins admins);

    Admins selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admins admins);

    int updateByPrimaryKey(Admins admins);
}