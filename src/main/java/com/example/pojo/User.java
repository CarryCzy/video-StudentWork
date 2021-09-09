package com.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * user 用户实体层
 * @author yxy
 */
@Data
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private Integer id;

    private String username;

    private String phone;

    private String password;

    private String img;  //头像地址

    private Integer age;

    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

    private String sex;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}