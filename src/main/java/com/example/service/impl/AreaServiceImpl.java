package com.example.service.impl;

import com.example.mapper.AreaMapper;
import com.example.pojo.Area;
import com.example.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Resource
    private AreaMapper areaMapper;
    @Override
    public List<Area> selectAll() {
        return areaMapper.selectAll();
    }
}
