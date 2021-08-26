package com.example.service;

import com.example.pojo.Actor;

import java.util.List;

public interface ActorService {
    //根据Id删除演员
    int deleteByPrimaryKey(Integer id);
    //添加演员
    int insertSelective(Actor record);
    //查询演员信息
    Actor selectByPrimaryKey(Integer id);
    //修改演员信息
    int updateByPrimaryKeySelective(Actor record);
    //演员集合/条件查询演员集合(演员编号，演员姓名)
    public List<Actor> queryActor(Actor actor);

}
