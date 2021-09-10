package com.example.service.impl;

import com.example.mapper.HistoryMapper;
import com.example.pojo.History;
import com.example.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 历史记录service接口实现类
 */
@Service
public class HistoryServiceImp implements HistoryService {

    @Resource
    private HistoryMapper historyMapper;

    @Override
    public List<History> selectByUid(Integer uid) {
        return historyMapper.selectByUid(uid);
    }

    @Override
    public int addToHistory(History history) {
        return historyMapper.insertSelective(history);
    }
}
