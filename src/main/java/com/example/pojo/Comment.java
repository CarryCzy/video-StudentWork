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

    public Comment() {
    }

    public Comment(Integer id, Integer vid, Integer uid, String comment) {
        this.id = id;
        this.vid = vid;
        this.uid = uid;
        this.comment = comment;
    }

    public Comment(Integer vid, Integer uid, String comment) {
        this.vid = vid;
        this.uid = uid;
        this.comment = comment;
    }
}