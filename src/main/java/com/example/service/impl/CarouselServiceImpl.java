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
    public List<Carousel> getAllByStatus(Integer status) {
        return carouselMapper.getAllByStatus(status);
    }

    @Override
    public List<Carousel> getAll(Integer start, Integer limit) {
        return carouselMapper.getAll(start,limit);
    }

    @Override
    public List<Carousel> getByCondition(Carousel carousel, Integer start, Integer limit) {
        return carouselMapper.getByCondition(carousel, start, limit);
    }

    @Override
    public Integer getCountByCondition(Carousel carousel) {
        return carouselMapper.getCountByCondition(carousel);
    }

    @Override
    public int updateStatus(int id,int status) {
        return carouselMapper.updateStatus( id, status);
    }

    @Override
    public int deleteById(int id) {
        return carouselMapper.deleteById(id);
    }
}
