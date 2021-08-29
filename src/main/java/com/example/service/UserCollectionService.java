package com.example.service;

import com.example.pojo.UserCollection;

import java.util.List;

public interface UserCollectionService {
    int insert(UserCollection userCollection);

    int insertSelective(UserCollection userCollection);

    /*根据用户id 查询收藏视频的list c*/
    List<UserCollection> getById(int id);

    /*添加用户收藏信息 c*/
    int add(UserCollection collection);

    /*删除用户收藏信息 c*/
    int del(int uid,int vid);
}
