package com.example.controller;

import com.example.pojo.User;
import com.example.pojo.UserCollection;
import com.example.service.UserCollectionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserCollectionController {
    @Resource
    private UserCollectionService userCollectionService;

    /**
     * 根据用户id 查询收藏视频的list c
     * @param uid
     * @return
     */
    @RequestMapping("collection/get")
    @ResponseBody
    public List<UserCollection> get(int uid){
        List<UserCollection> collections = userCollectionService.getById(uid);
        return collections;
    }

    /**
     * 根据视频id 添加收藏记录 c
     * @param vid
     * @return
     */
    @RequestMapping("collection/add")
    @ResponseBody
    public void add(HttpSession session,int vid){
        User user = (User) session.getAttribute("user");
        UserCollection collection = new UserCollection(user.getId(),vid);
        userCollectionService.add(collection);
    }

    /**
     * 根据视频id删除收藏记录 c
     * @param vid
     * @return
     */
    @RequestMapping("collection/del")
    @ResponseBody
    public void del(HttpSession session,int vid){
        User user = (User) session.getAttribute("user");
        userCollectionService.del(user.getId(),vid);

    }

}
