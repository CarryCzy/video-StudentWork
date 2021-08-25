package com.example.mapper;

import com.example.pojo.UserCollection;

public interface UserCollectionMapper {
    int insert(UserCollection record);

    int insertSelective(UserCollection record);
}