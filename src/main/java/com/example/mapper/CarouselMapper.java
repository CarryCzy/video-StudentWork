package com.example.mapper;

import com.example.pojo.Carousel;

import java.util.List;

public interface CarouselMapper {
    //通过状态查询全部
    List<Carousel> getAllByStatus(Integer status);
}