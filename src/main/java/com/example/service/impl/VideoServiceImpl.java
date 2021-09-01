package com.example.service.impl;

import com.example.mapper.CategoryMapper;
import com.example.mapper.VideoDetailMapper;
import com.example.mapper.VideoMapper;
import com.example.page.PageInfo;
import com.example.pojo.Video;
import com.example.pojo.VideoDetail;
import com.example.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private VideoDetailMapper videoDetailMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Video> getAll( PageInfo<Video> pageInfo) {
        int pageSize=pageInfo.getPageSize();
        int pageNum = pageInfo.getPageNum();
        int start = pageSize * (pageNum-1);
        return videoMapper.selectAll(start,pageSize);
    }

    @Override
    public int getCount() {
        return videoMapper.selectCount();
    }

    @Override
    public List<Video> getByName(String name,PageInfo pageInfo) {
        int start = (pageInfo.getPageNum()-1) * pageInfo.getPageSize();
        System.out.println(start);
        return videoMapper.getByNameAndPage(name,start,pageInfo.getPageSize());
    }

    @Override
    public List<Video> getAllVideos() {
        return videoMapper.findVideoAndCategory();
    }

    @Override
    public List<Video> getAllVideosByCondition(Video video,String year) {
        Integer temp = null;
        Integer pre = null;
        Integer last = null;

        if(year.equals("更早")){
            last = 1999;
        }else if(year.equals("2010-2000")){
            pre = 2000;
            last = 2010;
        }else if (!year.equals("")){
            temp = Integer.parseInt(year);
        }
        return videoMapper.findVideoAndCategoryByCondition(video,temp,pre,last);
    }

    @Override
    public List<Video> getByName(String name) {
        return videoMapper.getByName(name);
    }

    @Override
    public int getCountByName(String name) {
        return videoMapper.getCountByName(name);
    }

    @Override
    public int getCountByCondition(int type, int category, String location, String year) {
        Integer temp = null;
        Integer pre = null;
        Integer last = null;

        if(year.equals("更早")){
            last = 1999;
        }else if(year.equals("2010-2000")){
            pre = 2000;
            last = 2010;
        }else if (!year.equals("全部") && !year.equals("")){
            temp = Integer.parseInt(year);
        }
        return videoMapper.selectCountByCondition(type,category,location,temp,pre,last);
    }

    @Override
    public List<Video> getAllByCondition(int type, int category, String location, String year, PageInfo<Video> pageInfo) {
        Integer temp = null;
        Integer pre = null;
        Integer last = null;
        if(year.equals("更早")){
            last = 1999;
        }else if(year.equals("2010-2000")){
            pre = 2000;
            last = 2010;
        }else if (!year.equals("全部") && !year.equals("")){
            temp = Integer.parseInt(year);
        }
        int pageSize=pageInfo.getPageSize();
        int pageNum = pageInfo.getPageNum();
        int start = pageSize * (pageNum-1);
        List<Video> videos = videoMapper.selectAllByCondition(type, category, location, temp,start,pageSize,pre,last);
        pageInfo.setList(videos);
        return videos;
    }

    @Override
    public Video getById(int id) {
        return videoMapper.selectById(id);
    }

    @Override
    public Video getVideoWithCategory(int id) {
        return videoMapper.findVideoWithCatrgory(id);
    }
    /*根据视频id获取分集信息 c*/
    public List<VideoDetail> getDetailById(int id){
        return videoDetailMapper.getByVideoId(id);
    }

    @Override
    public VideoDetail getDetailByDetailId(int id) {
        return videoDetailMapper.getById(id);
    }

    @Override
    public void insVideo(String name,Integer totalEpisode,Integer currentEpisode,String imgPath, Integer type2, List<Integer> category, String starring, Integer year, String location, Integer statue,String description) {
        videoMapper.insVideo(name,totalEpisode,currentEpisode, type2,starring, year, location, statue, imgPath,description);

        int vid = videoMapper.selectIdByName(name);
        for (int i =0 ;i<category.size();i++){
            if (category.get(i) != null){
                categoryMapper.insCategory(vid,category.get(i));
            }
        }
    }

    @Override
    public int updVideo(int vid,String name,Integer totalEpisode,Integer currentEpisode,String imgPath, Integer type2, List<Integer> category, String starring, Integer year, String location, Integer statue,String description) {
        int result = videoMapper.updVideo(name,totalEpisode,currentEpisode, type2,starring, year, location, statue, imgPath,description,vid);

        categoryMapper.delCategory(vid);
        for (int i =0 ;i<category.size();i++){
            if (category.get(i) != null){
                categoryMapper.insCategory(vid,category.get(i));
            }
        }
        return result;
    }

    @Override
    public int insVideoDetail(int id, String title, int episode) {
        return videoDetailMapper.insVideoDetail(id,title,episode);
    }

    @Override
    public int updDetailById(VideoDetail detail) {
        return videoDetailMapper.updByDetail(detail);
    }

    @Override
    public int delAll(Integer id) {
        return videoMapper.del(id);
    }

    @Override
    public void delDetail(Integer id) {
        videoDetailMapper.del(id);
    }

    @Override
    public int delDetailByDetailId(Integer id) {
        return videoDetailMapper.delByDetailId(id);
    }

    @Override
    public int updateVideoArea(Integer id) {
        return videoMapper.updateVideoArea(id);
    }
    @Override
    public List<Video> selVideosDate() {
        return videoMapper.selVideosDate();
    }

    @Override
    public List<Video> selUserCollection(Integer uid) {
        return videoMapper.selUserCollection(uid);
    }

    @Override
    public int getVIdByName(String name) {
        return videoMapper.getVIdByName(name);
    }
}
