package com.example.controller;

import com.example.pojo.History;
import com.example.pojo.User;
import com.example.service.HistoryService;
import com.example.service.UserService;
import com.example.service.VideoService;
import com.example.utils.CodeImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用户控制层
 * @author yxy
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private HistoryService historyService;

    @Resource
    private VideoService videoService;

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
    @RequestMapping(value = "/login/{username}/{password}/{code}", produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
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
                    s = "密码输入有误";
                }
            } else {
                s = "验证码输入有误";
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
        userService.insertSelective(user);
        return "redirect:/index.jsp";
    }

    /**
     * 跳转个人中心
     * @param uid
     * @param model
     * @return sessionUser collection histories
     */
    @RequestMapping("/home")
    public String toUserHome(@RequestParam("id") Integer uid, Model model){
        model.addAttribute("histories", historyService.selectByUid(uid));
        model.addAttribute("collections", videoService.selUserCollection(uid));
        return "user/home";
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public String update(User user, HttpSession session){
        userService.updateByPrimaryKeySelective(user);
        session.setAttribute("user", user);
        return "user/home";
    }

    /**
     * 上传头像
     * @param file
     * @param request
     * @return res{code,src}
     */
    @RequestMapping("/imageUpload")
    @ResponseBody
    public Map<String, Object> imageUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        Map<String, Object> res = new HashMap<>();
        if (file.isEmpty()){
            res.put("code",0);
        } else {
            String oldName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("/file/user/avatar");
//            System.out.println(path);
            //文件重命名
            String fileName = changeName(oldName);
            //图片路径
            String rappendix = "file/user/avatar/" + fileName;
            //上传文件
            fileName = path + "/" + fileName;
            File file1 = new File(fileName);
            try {
                file.transferTo(file1);
                //返回值
                res.put("code",1);
                res.put("src", rappendix);
            } catch (IOException e) {
                res.put("code",0);
            }
        }
        return res;
    }

    /**
     * 修改头像
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/imageUpdate")
    @ResponseBody
    public Map<String, Object> imageUpdate(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpSession session){
        Map<String, Object> res = new HashMap<>();
        if (file.isEmpty()){
            res.put("code",0);
        } else {
            String oldName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("/file/user/avatar");
            //文件重命名
            String fileName = changeName(oldName);
            //图片路径
            String rappendix = "file/user/avatar/" + fileName;
            //上传文件
            fileName = path + "/" + fileName;
            File file1 = new File(fileName);
            try {
                file.transferTo(file1);
                User user = (User)session.getAttribute("user");
                if (user != null) {
                    user.setImg(rappendix);
                    userService.updateByPrimaryKeySelective(user);
                    //返回值
                    res.put("code",1);
                    res.put("src", rappendix);
                }
            } catch (IOException e) {
                res.put("code",0);
            }
        }
        return res;
    }
    /**
     * 随机数修改文件名
     * @param oldName
     * @return newName
     */
    public static String changeName(String oldName){
        Random r = new Random();
        Date d = new Date();
        String newName = oldName.substring(oldName.indexOf('.'));
        newName = r.nextInt(99999999) + d.getTime() + newName;
        return newName;
    }

    /**
     * 添加到历史记录
     * @param history
     */
    @RequestMapping("/addHistory")
    public void addHistory(History history){
        historyService.addToHistory(history);
    }
}
