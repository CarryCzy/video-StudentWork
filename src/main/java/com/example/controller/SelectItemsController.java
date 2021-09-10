package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.pojo.Actor;
import com.example.pojo.Area;
import com.example.pojo.Category;
import com.example.pojo.Video;
import com.example.service.ActorService;
import com.example.service.AreaService;
import com.example.service.CategoryService;
import com.example.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("selectItems")
public class SelectItemsController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private AreaService areaService;

    @Resource
    private VideoService videoService;

    @Resource
    private ActorService actorService;

    /**
     * 动态获取所有频道
     * @param response
     */
    @RequestMapping("/getPd")
    public void getPd(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        List<Category> list = categoryService.selByPid(0);
        String jsonString = JSONArray.toJSONString(list);
        response.getWriter().println(jsonString);
    }

    /**
     * 根据pid获取分类
     * @param response
     * @param pid
     * @throws IOException
     */
    @RequestMapping("/getByPid/{pid}")
    public void getByPid(HttpServletResponse response, @PathVariable("pid")Integer pid) throws IOException {
        response.setCharacterEncoding("utf-8");
        List<Category> list = categoryService.selByPid(pid);
        String jsonString = JSONArray.toJSONString(list);
        response.getWriter().println(jsonString);
    }

    /**
     * 获取全部地区
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getArea")
    public void getArea(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        List<Area> list = areaService.selectAll();
        String jsonString = JSONArray.toJSONString(list);
        response.getWriter().println(jsonString);
    }
    /**
     * 获取全部演员
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getActor")
    public void getAcotr(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        List<Actor> list =actorService.selectAll();
        String jsonString = JSONArray.toJSONString(list);
        response.getWriter().println(jsonString);
    }
    /**
     * 获取现有年代
     * @throws IOException
     */
    @RequestMapping("/getDate")
    @ResponseBody
    public String getDate() throws IOException {
        List<Video> list = videoService.selVideosDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            Date date = list.get(i).getPublishDate();
            s += format.format(date) + "/";
        }
        return s;
    }

}
