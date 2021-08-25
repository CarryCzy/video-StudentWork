package com.example.mapper;

import com.example.pojo.Admins;

public interface AdminsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admins admins);

    int insertSelective(Admins admins);

    Admins selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admins admins);

    int updateByPrimaryKey(Admins admins);
}