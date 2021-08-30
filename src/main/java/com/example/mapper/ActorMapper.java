package com.example.mapper;

import com.example.pojo.Actor;
import com.example.pojo.Category;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ActorMapper {
    //根据Id删除演员
    public int deleteByPrimaryKey(Integer id);
    //添加演员
    public int insertSelective(Actor record);
    //查询演员信息
    public Actor selectByPrimaryKey(Integer id);
    //修改演员信息
    public int updateByPrimaryKeySelective(Actor record);
    //演员集合/条件查询演员集合(演员编号，演员姓名)
    public List<Actor> queryActor(Actor actor);
    //演员列表集合
    public List<Actor> getAll(Integer start, Integer limit);
    //根据条件查询演员分类
    public List<Actor> getByName(Actor actor,Integer start,Integer limit);
    //根据条件统计演员数量
    public int getActorCount(Actor actor);
    //统计演员数量
    public int getCount();
    //删除演员，同时删除视频演员联合表
    @Delete("delete a,va from actor a left join video_actor va on a.id = va.actor_id where id = #{id}")
    public int del(Integer id);
}