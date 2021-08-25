package com.example.service.impl;

import com.example.mapper.CategoryMapper;
import com.example.pojo.Category;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public int delCategory(Integer id) {
        return categoryMapper.delCategory(id);
    }

    @Override
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int insertSelective(Category category) {
        return categoryMapper.insertSelective(category);
    }

    @Override
    public Category selectByPrimaryKey(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Category category) {
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int updateByPrimaryKey(Category category) {
        return categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public void insCategory(int vid, Integer integer) {
        categoryMapper.insCategory(vid,integer);
    }
}
