package com.example.service.impl;

import com.example.mapper.CategoryMapper;
import com.example.pojo.Category;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public int delCategory(Integer id) {
        return categoryMapper.delCategory(id);
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
    public void insCategory(int vid, Integer integer) {
        categoryMapper.insCategory(vid,integer);
    }

    @Override
    public List<Category> selByPid(Integer pid) {
        return categoryMapper.selByPid(pid);
    }

    @Override
    public String getNameById(Integer id) {
        return categoryMapper.getNameById(id);
    }
}
