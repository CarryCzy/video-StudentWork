package com.example.pojo;

import java.io.Serializable;

/**
 * user_collection
 * @author 
 */
public class UserCollection implements Serializable {
    private Integer uid;

    private Integer vid;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public UserCollection() {
    }

    public UserCollection(Integer uid, Integer vid) {
        this.uid = uid;
        this.vid = vid;
    }
}