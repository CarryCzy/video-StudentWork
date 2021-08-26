package com.example.service.impl;

import com.example.mapper.AdminsMapper;
import com.example.pojo.Admins;
import com.example.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminsServiceImpl implements AdminsService {
    @Autowired
    AdminsMapper adminsMapper;

    @Override
    public Admins login(Admins admins) {
        return adminsMapper.login(admins);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return adminsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Admins admins) {
        return adminsMapper.insert(admins);
    }

    @Override
    public int insertSelective(Admins admins) {
        return adminsMapper.insertSelective(admins);
    }

    @Override
    public Admins selectByPrimaryKey(Integer id) {
        return adminsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Admins admins) {
        return adminsMapper.updateByPrimaryKeySelective(admins);
    }

    @Override
    public int updateByPrimaryKey(Admins admins) {
        return adminsMapper.updateByPrimaryKey(admins);
    }
}
