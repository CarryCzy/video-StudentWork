package com.example.service.impl;

import com.example.mapper.UserCollectionMapper;
import com.example.pojo.UserCollection;
import com.example.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<UserCollection> getById(int id) {
        return userCollectionMapper.getById(id);
    }

    @Override
    public int add(UserCollection collection) {
        return userCollectionMapper.insert(collection);
    }

    @Override
    public int del(int uid, int vid) {
        return userCollectionMapper.del(uid, vid);
    }
}
