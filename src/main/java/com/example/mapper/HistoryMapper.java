package com.example.mapper;

import com.example.pojo.History;

import java.util.List;

public interface HistoryMapper {

    //用户id查询历史记录
    List<History> selectByUid(Integer uid);

    int deleteByPrimaryKey(Integer id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);
}