package com.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * carousel 轮播图实体
 */
@Data
@Getter
@Setter
public class Carousel {
    private Integer id;

    private String title;  //文字说明

    private String imgSrc;  //图片路径

    private Integer status; //图片状态 0：不显示  1：显示
}