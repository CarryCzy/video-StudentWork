package com.example.service;

import com.example.pojo.UserCollection;

public interface UserCollectionService {
    int insert(UserCollection userCollection);

    int insertSelective(UserCollection userCollection);
}
