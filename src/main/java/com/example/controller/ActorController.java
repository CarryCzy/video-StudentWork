package com.example.controller;

import com.example.pojo.Actor;
import com.example.pojo.Category;
import com.example.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/actor")
public class ActorController {
    @Resource
    private ActorService actorServiceImpl;
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();
    @RequestMapping("/list")
    public String actor(){
        return "manager/actorList";
    }
    /**
     * 演员分页数据，返回数据给页面json
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value="getAll")
    @ResponseBody
    public Map<String,Object> getAllCategory(Integer page, Integer limit){
        int start = (page-1)*limit;
        List<Actor> list = actorServiceImpl.getAll(start,limit);
        int count = actorServiceImpl.getCount();
        if (list.size() > 0){
            result.put("code",0);
            result.put("data",list);
            result.put("count",count);
        }else {
            result.put("code",1);
        }
        return result;
    }

    /**
     * 根据条件查询演员分页，
     * @param actor 演员查询条件
     * @param page 页面
     * @param limit 每页条数
     * @return
     */
    @RequestMapping("getByCondition")
    @ResponseBody
    public Map<String, Object> getByName(Actor actor,Integer page,Integer limit){
        Map<String,Object> map = new HashMap<>();
        Integer start = (page-1)*limit;
        List<Actor> list = actorServiceImpl.getByName(actor,start,limit);
        Integer count = actorServiceImpl.getActorCount(actor);
        if (list.size() > 0){
            map.put("code",0);
            map.put("data",list);
            map.put("count",count);
        }else {
            map.put("code",1);
        }
        return map;
    }
    /**
     * 删除演员功能
     * @param id
     * @return 0/1 是否删除成功
     */
    @RequestMapping("delActor/{id}")
    @ResponseBody
    public Integer delActor(@PathVariable("id")Integer id){
        int i = actorServiceImpl.deleteByPrimaryKey(id);
        return i;
    }
    /**
     * 根据Id查看单个分类信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("detail/{id}")
    public String categoryDetail(@PathVariable("id")Integer id, HttpServletRequest request){
        Actor actor = actorServiceImpl.selectByPrimaryKey(id);
        request.setAttribute("actor",actor);
        return "manager/actor_detail";
    }
    //    @RequestMapping("category/list")
//    public String category(){
//        return "manager/categoryList";
//    }
//    @RequestMapping("category/list")
//    public String category(HttpServletRequest request){
////        List<Category> type = categoryServiceImpl.selectAll();
//        List<Category> type = categoryServiceImpl.selByPid(0);
//        request.setAttribute("type",type);
////        request.setAttribute("type",type);
//        return "manager/categoryList";
//    }
    @RequestMapping("/addActor")
    @ResponseBody
    public Integer addActor(Actor actor){
        int i = actorServiceImpl.insertSelective(actor);
        return i;
    }
}
