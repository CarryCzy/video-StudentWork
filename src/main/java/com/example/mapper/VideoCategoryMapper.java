package com.example.mapper;

import com.example.pojo.VideoCategory;

public interface VideoCategoryMapper {
    int insert(VideoCategory record);

    int insertSelective(VideoCategory record);
}