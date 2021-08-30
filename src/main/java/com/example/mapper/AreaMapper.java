package com.example.mapper;

import com.example.pojo.Area;
import com.example.pojo.Category;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface AreaMapper {
    //地区集合
    List<Area> selectAll();
    //根据Id删除地区
    int deleteByPrimaryKey(Integer id);
    //添加地区
    int insertSelective(Area record);
    //根据id查询单个地区信息
    Area selectByPrimaryKey(Integer id);
    //修改地区
    int updateByPrimaryKeySelective(Area record);
    //地区列表集合分页
    public List<Area> getAll(Integer start, Integer limit);
    //根据条件查询地区分页
    public List<Area> getByName(Area area,Integer start,Integer limit);
    //获取地区列表总数
    public int getCount();
    //根据条件查询，获取地区列表数量
    public int getCountByName(Area area);
    @Delete("delete a,va from area a left join video_area va on a.id = va.area_id")
    public int delArea(Integer id);
}