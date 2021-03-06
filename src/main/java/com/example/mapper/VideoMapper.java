package com.example.mapper;

import com.example.pojo.Category;
import com.example.pojo.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VideoMapper {
    @Select("select * from videos")
    List<Video> selectAllVideos();

    @Select("select * from videos limit #{param1},#{param2}")
    List<Video> selectAll(int start,int pageSize);
    @Select("select count(*) from videos")
    int selectCount();

    /*根据id获取视频 c*/
    Video selectById(int id);

    List<Category> selectCategoryByVideoId(int id);

    List<Video> selectAllByCondition(int type, int category, String location, Integer year,int start,int pageSize,Integer pre,Integer last);
    int selectCountByCondition(int type, int category, String location, Integer year,Integer pre,Integer last);

//    @Select("select * from videos where name like concat('%',#{param1},'%')")
//    List<Video> selectByName(String name);
//    @Select("select * from videos where name like concat('%',#{param1},'%') limit #{param2},#{param3}")
//    List<Video> selectByNameAndPage(String name,int start,int pageSize);
//    @Select("select count(*) from videos where name like concat('%',#{param1},'%')")
//    int selectCountByName(String name);

    /*根据视频名称查询视频 c*/
    List<Video> getByName(String name);
    /*根据视频名称和分页信息搜索视频 c*/
    List<Video> getByNameAndPage(String name,int start,int pageSize);
    /*根据视频名称查询视频数 c*/
    int getCountByName(String name);

    Video findVideoWithCatrgory(int id);
    List<Category> findCategoryWithVideo(int id);

    List<Video> findVideoAndCategory();

    List<Video> findVideoAndCategoryByCondition(Video video, Integer year,Integer pre,Integer last);


    void insVideo(@Param("name") String name,@Param("totalEpisode") Integer totalEpisode,@Param("currentEpisode") Integer currentEpisode, @Param("type") Integer type, @Param("starring") String starring
            , @Param("year") Integer year, @Param("location") String location
            , @Param("statue") Integer statue, @Param("imgPath") String imgPath
            ,@Param("description")String description);

    @Select("select id from videos where name = #{name}")
    int selectIdByName(String name);

    int updVideo(String name, Integer totalEpisode, Integer currentEpisode, Integer type2, String starring, Integer year, String location, Integer statue, String imgPath, String description,int id);

    @Delete("delete v,vc,uc,history from videos v " +
            "left join video_category vc on v.id = vc.video_id " +
            "left join user_collection uc on v.id = uc.vid " +
            "left join history on v.id = history.vid " +
            "where v.id = #{id}")
    int del(Integer id);
    //删除地区后，修改视频表的地区为空
    @Update("update videos v left join area a on v.location = a.name left join video_area va on " +
            "a.id = va.area_id set v.location = null where a.id=#{id}")
    public int updateVideoArea(Integer id);
    //查询视频年代（过滤重复）yxy
    List<Video> selVideosDate();
    //查询用户收藏 yxy
    List<Video> selUserCollection(Integer uid);

    //根据视频名查询用户id
    int getVIdByName(String name);
}