package com.example.mapper;

import com.example.pojo.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment comment);

    int insertSelective(Comment comment);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment comment);

    int updateByPrimaryKey(Comment comment);

    /*查询评论的总数 c*/
    int getCountByVid(int vid);

    /*根据视频id查询评论并分页 c*/
    List<Comment> getAllByVid(int vid, int start, int pageSize);

    /**
     * 分页查询评论（联表查询）
     * @param comment
     * @param start
     * @param limit
     * @return
     */
    List<Comment> selCommentByCondition(Comment comment, int start, int limit);
    //根据uid获取用户名
    String getUserNameByUId(Integer uId);
    //根据vid获取视频名
    String getVideoNameByVId(Integer vId);
    //根据条件获取评论总数
    Integer getCount(Comment comment);

}