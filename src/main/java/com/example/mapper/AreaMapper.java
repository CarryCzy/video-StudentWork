package com.example.mapper;

import com.example.pojo.Area;

import java.util.List;

public interface AreaMapper {

    List<Area> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}