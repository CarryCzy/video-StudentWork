package com.example.service.impl;

import com.example.mapper.CarouselMapper;
import com.example.pojo.Carousel;
import com.example.service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图service接口实现类
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> getAll() {
        return carouselMapper.getAll();
    }
}
