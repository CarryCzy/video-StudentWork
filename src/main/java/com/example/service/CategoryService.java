package com.example.service;

import com.example.pojo.Category;

import java.util.List;

public interface CategoryService {
    int delCategory(Integer id);

    int insertSelective(Category category);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category category);

    void insCategory(int vid, Integer integer);

    public List<Category> selByPid(Integer pid);

    public String getNameById(Integer id);
}
