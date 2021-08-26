package com.example.service.impl;

import com.example.mapper.ActorMapper;
import com.example.pojo.Actor;
import com.example.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorMapper actorMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return actorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Actor record) {
        return actorMapper.insertSelective(record);
    }

    @Override
    public Actor selectByPrimaryKey(Integer id) {
        return actorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Actor record) {
        return actorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Actor> queryActor(Actor actor) {
        return actorMapper.queryActor(actor);
    }
}
