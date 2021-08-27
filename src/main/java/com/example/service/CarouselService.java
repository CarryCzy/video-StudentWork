package com.example.service;

import com.example.pojo.Carousel;

import java.util.List;

/**
 * 轮播图service接口
 */
public interface CarouselService {
    //通过状态查询全部
    List<Carousel> getAllByStatus(Integer status);
}
