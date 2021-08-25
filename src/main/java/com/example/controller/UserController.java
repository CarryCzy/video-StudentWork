package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.CodeImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取验证码
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        Object[] objs = CodeImageUtil.createImage();
        String code = (String) objs[0];
        BufferedImage image = (BufferedImage) objs[1];
        OutputStream os = response.getOutputStream();
        ImageIO.write(image,"png",os);
        session.setAttribute("userLoginCode",code);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param code
     * @return
     */
    @RequestMapping("/login/{username}/{password}/{code}")
    @ResponseBody
    public String login(@PathVariable("username")String username, @PathVariable("password")String password, @PathVariable("code")String code, HttpSession session){
        String s = "";
        User user1 = userService.selectByUsername(username);
        //判断用户名是否存在
        if (user1 != null) {
            String userLoginCode = session.getAttribute("userLoginCode").toString();
            //判断验证码
            if (userLoginCode.equalsIgnoreCase(code)) {
                User user = userService.login(new User(username,password));
                //判断密码
                if (user != null) {
                    s = "success";
                    session.setAttribute("user",user);
                } else {
                    s = "pwd";
                }
            } else {
                s = "code";
            }
        }
        return s;
    }

    /**
     * 退出登录
     * @param session
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.setAttribute("user",null);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user){
        int i = userService.insertSelective(user);
        return "redirect:index.jsp";
    }

    /**
     * 跳转个人中心
     * @param id
     * @param model
     * @return sessionUser conllection history
     */
    @RequestMapping("/home")
    public String toUserHome(@RequestParam("id") Integer id, Model model){

        return "user/home";
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public String update(User user){
        userService.updateByPrimaryKeySelective(user);
        return "user/home";
    }
}
