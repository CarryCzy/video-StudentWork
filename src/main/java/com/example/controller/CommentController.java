package com.example.controller;

import com.example.page.PageInfo;
import com.example.pojo.Comment;
import com.example.pojo.User;
import com.example.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    /**
     * 添加评论 c
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public void addcom(HttpSession session, int vid, String comment){
        User user = (User) session.getAttribute("user");
        Comment comment1 = new Comment(vid,user.getId(),comment);
        commentService.insert(comment1);
    }

    /**
     * 获取所有评论并分页 c
     * @return
     */
    @RequestMapping("getAll/{vid}/{pageNum}/{pageSize}")
    @ResponseBody
    public PageInfo<Comment> getAll(@PathVariable("vid")int vid, @PathVariable("pageNum")int pageNum, @PathVariable("pageSize")int pageSize){
        if (pageNum <= 0){
            pageNum = 1;
        }
        if (pageSize <= 0){
            pageSize = 30;
        }
        PageInfo<Comment> pageInfo = new PageInfo<Comment>();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        pageInfo.setCount(commentService.getCountByVid(vid));
        pageInfo.setList(commentService.getAllByVid(vid,pageInfo));

        return pageInfo;
    }
}
