package com.example.mapper;

import com.example.pojo.UserCollection;

import java.util.List;

public interface UserCollectionMapper {
    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    /*根据用户id 查询收藏视频的list c*/
    List<UserCollection> getById(int uid);

    /*删除用户收藏信息 c*/
    int del(int uid,int vid);
}