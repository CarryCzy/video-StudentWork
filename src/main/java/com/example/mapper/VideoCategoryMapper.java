package com.example.mapper;

import com.example.pojo.VideoCategory;

public interface VideoCategoryMapper {

    int insertSelective(VideoCategory record);

    public int delVideoCategory(Integer id);
}