package com.example.mapper;

import com.example.pojo.VideoDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VideoDetailMapper {
    /*根据视频id获取分集信息 c*/
    public List<VideoDetail> getByVideoId(Integer pid);

    public VideoDetail getById(Integer id);

    public void del(Integer pid);

    public int delByDetailId(Integer id);

    public int insVideoDetail(Integer pid, String title, Integer episode);

    public int updByDetail(VideoDetail videoDetail);
}
