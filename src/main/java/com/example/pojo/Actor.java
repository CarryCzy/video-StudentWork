package com.example.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * actor 演员类
 * @author LHB
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actor implements Serializable {
    //演员编号
    private Integer id;
    //演员头像路径
    private String img_src;
    //演员姓名
    private String name;
    //演员粉丝量
    private Integer fans;
    //演员热度
    private Integer trend;
    //演员作品数量
    private Integer video_number;

    private static final long serialVersionUID = 1L;

    public Actor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}