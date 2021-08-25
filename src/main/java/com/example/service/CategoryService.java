package com.example.service;

import com.example.pojo.Category;

public interface CategoryService {
    int delCategory(Integer id);

    int insert(Category category);

    int insertSelective(Category category);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category category);

    int updateByPrimaryKey(Category category);

    void insCategory(int vid, Integer integer);
}
