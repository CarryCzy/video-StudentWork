package com.example.service;

import com.example.pojo.VideoCategory;

public interface VideoCategoryService {
    int insertSelective(VideoCategory record);

    public int delVideoCategory(Integer id);
}
