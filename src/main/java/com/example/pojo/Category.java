package com.example.pojo;

import java.io.Serializable;

/**
 * category
 * @author 
 */
public class Category {
    private Integer id; //频道或分类id

    private String categoryName; //频道名或分类名

    private Integer pid;  //分类--频道自连接

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Category(Integer id, String categoryName, Integer pid) {
        this.id = id;
        this.categoryName = categoryName;
        this.pid = pid;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", pid=" + pid +
                '}';
    }
}