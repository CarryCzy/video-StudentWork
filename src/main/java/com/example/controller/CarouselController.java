package com.example.controller;

import com.example.page.PageInfo;
import com.example.pojo.Carousel;
import com.example.service.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
    @ResponseBody
    public PageInfo<Carousel> getAll(){
        PageInfo<Carousel> pageInfo = new PageInfo<Carousel>();
        pageInfo.setList(carouselService.getAll());
        return pageInfo;
    }
}
