package com.example.pojo;

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

    private Integer vid;

    private Integer uid;

    private String name;

    private Date time;

    private String img;

    private String title;

    private Integer episode;

}