package com.example.pojo;

import java.io.Serializable;

/**
 * admins
 * @author  QYM
 */
public class Admins {
    private Integer id;

    private String name;

    private String password;

    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Admins(Integer id, String name, String password, String img) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.img = img;
    }

    public Admins() {
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}