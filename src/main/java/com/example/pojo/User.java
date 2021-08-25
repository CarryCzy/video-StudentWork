package com.example.pojo;

import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * user 用户实体层
 * @author yxy
 */
@Data
@Getter
@Setter
public class User {
    private Integer id;

    private String username;

    private String phone;

    private String password;

    private String img;  //头像地址

    private Integer age;

    private Date birthday;

    private String sex;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}