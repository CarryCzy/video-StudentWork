package com.example.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    private Integer id;

    private Integer vid;

    private Integer uid;

    private String comment;

    private static final long serialVersionUID = 1L;
}