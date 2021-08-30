package com.example.service;

import com.example.pojo.Carousel;

import java.util.List;

/**
 * 轮播图service接口
 */
public interface CarouselService {
    //通过状态查询全部
    List<Carousel> getAllByStatus(Integer status);
    /**
     * 分页查询所有轮播图
     * @param start
     * @param limit
     * @return
     */
    List<Carousel> getAll(Integer start,Integer limit);
    /**
     * 根据条件分页查询所有轮播图
     * @param carousel
     * @param start
     * @param limit
     * @return
     */
    List<Carousel> getByCondition(Carousel carousel,Integer start,Integer limit);
    /**
     * 获取用户总数
     * @param carousel
     * @return
     */
    Integer getCountByCondition(Carousel carousel);

    /**
     * 更改轮播图状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(int id,int status);
    /**
     * 根据id删除轮博图
     * @param id
     * @return
     */
    int deleteById(int id);
}
