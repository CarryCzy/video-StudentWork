package com.example.service.impl;

import com.example.mapper.CommentMapper;
import com.example.page.PageInfo;
import com.example.pojo.Comment;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public int insertSelective(Comment comment) {
        return commentMapper.insertSelective(comment);
    }

    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public int updateByPrimaryKey(Comment comment) {
        return commentMapper.updateByPrimaryKey(comment);
    }

    @Override
    public int getCountByVid(int vid) {
        return commentMapper.getCountByVid(vid);
    }

    @Override
    public List<Comment> getAllByVid(int vid, PageInfo pageInfo) {
        int start = (pageInfo.getPageNum()-1)*pageInfo.getPageSize();
        return commentMapper.getAllByVid(vid,start,pageInfo.getPageSize());
    }

    @Override
    public List<Comment> selCommentByCondition(Comment comment, int start, int limit) {
        return commentMapper.selCommentByCondition(comment, start, limit);
    }

    @Override
    public String getUserNameByUId(Integer uId) {
        return commentMapper.getUserNameByUId(uId);
    }

    @Override
    public String getVideoNameByVId(Integer vId) {
        return commentMapper.getVideoNameByVId(vId);
    }

    @Override
    public Integer getCount(Comment comment) {
        return commentMapper.getCount(comment);
    }
}
