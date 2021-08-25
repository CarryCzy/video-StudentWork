package com.example.service.impl;

import com.example.mapper.UserCollectionMapper;
import com.example.pojo.UserCollection;
import com.example.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserCollectionServiceImpl implements UserCollectionService {
    @Autowired
    UserCollectionMapper userCollectionMapper;
    @Override
    public int insert(UserCollection userCollection) {
        return userCollectionMapper.insert(userCollection);
    }

    @Override
    public int insertSelective(UserCollection userCollection) {
        return userCollectionMapper.insertSelective(userCollection);
    }
}
