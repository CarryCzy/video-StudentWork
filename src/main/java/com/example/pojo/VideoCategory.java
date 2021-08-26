package com.example.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * video_category 作品分类
 * @author LHB
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoCategory implements Serializable {
    private Integer video_id;

    private Integer category_id;

    private static final long serialVersionUID = 1L;
}