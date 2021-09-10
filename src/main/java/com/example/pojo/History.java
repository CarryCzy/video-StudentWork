package com.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * history
 * @author yxy
 */
@Data
@Getter
@Setter
public class History {
    private Integer id;

    private Integer vid;  //视频id

    private Integer uid;  //用户id

    private String name; //视频名

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;  //观看时间

    private String img;  //视频图片

    private String title;  //分集名

    private Integer episode;  //分集

}