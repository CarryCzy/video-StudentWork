package com.example.service;

import com.example.pojo.Carousel;

import java.util.List;

/**
 * 轮播图service接口
 */
public interface CarouselService {
    //查询全部
    List<Carousel> getAll();
}
