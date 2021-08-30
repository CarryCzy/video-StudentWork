package com.example.service;

import com.example.page.PageInfo;
import com.example.pojo.Comment;

import java.util.List;

public interface CommentService {

    int deleteByPrimaryKey(Integer id);

    int insert(Comment comment);

    int insertSelective(Comment comment);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment comment);

    int updateByPrimaryKey(Comment comment);

    /*查询评论的总数 c */
    int getCountByVid(int vid);

    /*根据视频id查询评论并分页 c*/
    List<Comment> getAllByVid(int vid, PageInfo pageInfo);
}
