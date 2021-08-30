package com.example.service.impl;

import com.example.mapper.AreaMapper;
import com.example.pojo.Area;
import com.example.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public List<Area> selectAll() {
        return areaMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return areaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Area record) {
        return areaMapper.insertSelective(record);
    }

    @Override
    public Area selectByPrimaryKey(Integer id) {
        return areaMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Area record) {
        return areaMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Area> getAll(Integer start, Integer limit) {
        return areaMapper.getAll(start,limit);
    }

    @Override
    public List<Area> getByName(Area area, Integer start, Integer limit) {
        return areaMapper.getByName(area, start, limit);
    }

    @Override
    public int getCount() {
        return areaMapper.getCount();
    }

    @Override
    public int getCountByName(Area area) {
        return areaMapper.getCountByName(area);
    }

    @Override
    public int delArea(Integer id) {
        return areaMapper.delArea(id);
    }
}
