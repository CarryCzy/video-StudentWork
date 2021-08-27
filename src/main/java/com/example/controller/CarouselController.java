package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.page.PageInfo;
import com.example.pojo.Carousel;
import com.example.pojo.Category;
import com.example.service.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("carousel")
public class CarouselController {

    @Resource
    private CarouselService carouselService;

    /**
     * 获取所有轮播图对象
     * @return
     */
    @RequestMapping("/getAll")
    public void getAll(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        List<Carousel> list = carouselService.getAllByStatus(1);
        String jsonString = JSONArray.toJSONString(list);
        response.getWriter().println(jsonString);
    }
}
