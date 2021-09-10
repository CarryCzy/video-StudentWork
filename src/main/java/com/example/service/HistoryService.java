package com.example.service;

import com.example.pojo.History;

import java.util.List;

/**
 * 历史记录service接口
 */
public interface HistoryService {
    //用户id查询历史记录
    List<History> selectByUid(Integer uid);

    int addToHistory(History history);
}
