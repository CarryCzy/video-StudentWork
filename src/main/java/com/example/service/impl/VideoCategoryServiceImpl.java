package com.example.service.impl;

import com.example.mapper.VideoCategoryMapper;
import com.example.pojo.VideoCategory;
import com.example.service.VideoCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoCategoryServiceImpl implements VideoCategoryService {
    @Autowired
    private VideoCategoryMapper videoCategoryMapper;
    @Override
    public int insertSelective(VideoCategory record) {
        return videoCategoryMapper.insertSelective(record);
    }

    @Override
    public int delVideoCategory(Integer id) {
        return videoCategoryMapper.delVideoCategory(id);
    }
}
