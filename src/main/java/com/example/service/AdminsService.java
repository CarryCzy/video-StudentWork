package com.example.service;

import com.example.pojo.Admins;

public interface AdminsService {
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
