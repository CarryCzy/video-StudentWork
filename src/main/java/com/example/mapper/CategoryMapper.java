package com.example.mapper;

import com.example.pojo.Category;

public interface CategoryMapper {

    int delCategory(Integer id);

    int insert(Category category);

    int insertSelective(Category category);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category category);

    int updateByPrimaryKey(Category category);

    void insCategory(int vid, Integer integer);
}