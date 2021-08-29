package com.example.mapper;

import com.example.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    //删除分类
    int delCategory(Integer id);
    //根据Id查找分类
    Category selectByPrimaryKey(Integer id);
    //修改分类
    int updateByPrimaryKeySelective(Category category);
    //添加分类
    void insCategory(int vid, Integer integer);
    //添加分类
    int insertSelective(Category category);
    //根据视频编号查找分类集合
    public List<Category> selByPid(Integer pid);
    //根据分类编号查找分类名
    public String getNameById(Integer id);
    //视频列表集合
    public List<Category> getAll(Integer start,Integer limit);
    //根据条件查询视频分类
    public List<Category> getByName(Category category,Integer start,Integer limit);
    //根据条件统计分类数量
    public int getCategoryCount(Category category);
    //统计分类数量
    public int getCount();

    public List<Category> selectAll();
}