package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.*;
import com.example.service.*;
import com.example.service.impl.AreaServiceImpl;
import com.example.utils.CodeImageUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

@Controller
@RequestMapping("manager")
public class ManagerController {

    @Resource
    private VideoService videoServiceImpl;
    @Resource
    private AdminsService adminsService;
    @Resource
    private CategoryService categoryServiceImpl;
    @Resource
    private VideoCategoryService videoCategoryServiceImpl;
    @Resource
    private UserService userServiceImpl;
    @Resource
    private ActorService actorServiceImpl;
    @Resource
    private AreaService areaServiceImpl;
    //存储返回给页面的对象数据
    private Map<String, Object> result = new HashMap<>();


    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        Object[] objs = CodeImageUtil.createImage();
        String code = (String) objs[0];
        BufferedImage image = (BufferedImage) objs[1];

        OutputStream os = response.getOutputStream();
        ImageIO.write(image,"png",os);
        session.setAttribute("adminLoginCode",code);
    }

    /**
     * 管理员后台登录
     * @param admin
     * @param code
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(Admins admin, @RequestParam("code") String code,HttpServletRequest request) throws IOException {
        // 校验验证码信息
        String vcode = (String) request.getSession().getAttribute("adminLoginCode");
        if ("".equals(vcode)) {
            result.put("success", false);
            result.put("loginMsg", "长时间为操作,会话已失效,请刷新页面后重试!");
            result.put("loginCode", 0);
            return result;
        } else if (!code.equalsIgnoreCase(vcode)) {
            result.put("success", false);
            result.put("loginMsg", "验证码错误!");
            result.put("loginCode", 0);
            return result;
        }
        request.getSession().removeAttribute("adminLoginCode");
        //校验用户登录信息
        try {
            Admins admins = adminsService.login(admin);//验证账户和密码是否存在
            if (admins != null) {
                HttpSession session = request.getSession(); //将用户信息存储到Session
                session.setAttribute("admin", admins);
                result.put("success", true);
                result.put("loginCode", 1);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("loginMsg", "登录失败! 服务器端发生异常!");
            result.put("loginCode", 0);
            return result;
        }
        //登录失败
        result.put("success", false);
        result.put("loginMsg", "用户名或密码错误!");
        result.put("loginCode", 0);
        return result;
    }

    /**
     * 登录成功跳转至后台首页
     * @return
     */
    @RequestMapping("toIndex")
    public String toIndex(){
        return "redirect:/jsp/manager/index.jsp";
    }

    /**
     * 跳转至用户管理功能页面
     * @return
     */
    @RequestMapping("user/list")
    public String user(){
        return "manager/userlist";
    }

    /**
     * 得到用户分页数据，返回数据给页面json
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll(Integer page,Integer limit){
        int start = (page-1)*limit;
        List<User> list = userServiceImpl.getAll(start,limit);
        int count = userServiceImpl.getCount();
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
     * 查看用户功能
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("user/detail/{id}")
    public String userDetail(@PathVariable("id")Integer id,HttpServletRequest request){
        User user = userServiceImpl.selectById(id);
        System.out.println(user);
        request.setAttribute("user",user);
        return "manager/user_detail";
    }

    /**
     * 删除用户功能
     * @param id
     * @return
     */
    @RequestMapping("user/delete/{id}")
    @ResponseBody
    public Integer delUser(@PathVariable("id")Integer id){
        return userServiceImpl.deleteById(id);
    }

    @RequestMapping("getByCondition")
    @ResponseBody
    public Map<String, Object> getByCondition(User user,Integer page,Integer limit){
        Integer start = (page-1)*limit;
        List<User> list = userServiceImpl.getByCondition(user,start,limit);
        Integer count = userServiceImpl.getCountByCondition(user);
        if (list.size() > 0){
            result.put("code",0);
            result.put("data",list);
            result.put("count",count);
        }else {
            result.put("code",1);
        }
        return result;
    }
    @RequestMapping("video/list")
    public String video(HttpServletRequest request){
        List<Video> videos = videoServiceImpl.getAllVideos();
        List<Category> type = categoryServiceImpl.selByPid(0);
        request.setAttribute("videos",videos);
        request.setAttribute("type",type);
            return "manager/videolist";
    }


    @RequestMapping("video/search")
    public String search(HttpServletRequest request,Video video,String year){
        System.out.println(video);
        List<Video> videos = videoServiceImpl.getAllVideosByCondition(video,year);
        List<Category> types = categoryServiceImpl.selByPid(0);
        request.setAttribute("videos",videos);
        request.setAttribute("type",types);
        request.setAttribute("video",video);
        request.setAttribute("year",year);
        return "manager/videolist";
    }

    @RequestMapping("video/add")
    @ResponseBody
    public Map<String,Object> add(HttpServletRequest request, MultipartFile file
            , String name, @RequestParam(value = "type",required = false) Integer type2
            , @RequestParam(value = "category",required = false) List<Integer> category
            , String starring, Integer year, String location,Integer statue
            ,String description,Integer totalEpisode,Integer currentEpisode){

        String filename = file.getOriginalFilename();   //获取文件名
        String suffix = filename.substring(filename.lastIndexOf("."));
        String src = request.getServletContext().getRealPath("file/video/images/");
        File dest = new File(src+ "/" + filename);
        String imgPath = "file/video/images/" + filename;

        Map<String,Object> map = new HashMap<>();
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),dest);
            map.put("code",1);
        } catch (IOException e) {
            map.put("code",0);
            e.printStackTrace();
        }
        System.out.println(name+","+totalEpisode+","+currentEpisode+","+imgPath+","+type2+","+starring+","+","+year+","+location+","+statue+","+description);
        videoServiceImpl.insVideo(name,totalEpisode,currentEpisode,imgPath,type2,category,starring,year,location,statue,description);
        return map;
    }

    @RequestMapping("video/getVideoById")
    @ResponseBody
    public Video getVideoById(Integer id){
        return videoServiceImpl.getVideoWithCategory(id);
    }

    @RequestMapping("video/del")
    @ResponseBody
    public int delAll(Integer id,HttpServletRequest req){
        Video video = videoServiceImpl.getById(id);
        String imgPath = req.getServletContext().getRealPath(video.getImgSrc());
        if (imgPath != null && !imgPath.equals("")){
            File img = new File(imgPath);
            System.out.println(img);
            if (img.exists()){
                img.delete();
            }
        }

        List<VideoDetail> details = videoServiceImpl.getDetailById(id);
        videoServiceImpl.delDetail(id);
        for (VideoDetail detail : details){
            if (detail.getFileName() != null && !detail.getFileName().equals("")){
                File videoPath = new File(req.getServletContext().getRealPath("file/video/videos/"+detail.getFileName()));
                System.out.println(videoPath);
                if (videoPath.exists()){
                    videoPath.delete();
                }
            }
        }
        return videoServiceImpl.delAll(id);
    }

    @RequestMapping("video/update")
    @ResponseBody
    public Map<String,Object> update(HttpServletRequest request, MultipartFile file,Integer id
            , String name, @RequestParam(value = "type",required = false) Integer type2
            , @RequestParam(value = "category",required = false) List<Integer> category
            , String starring, Integer year, String location,Integer statue
            ,String description,Integer totalEpisode,Integer currentEpisode){
        int result;

        if (file != null){
            String filename = file.getOriginalFilename();   //获取文件名
            String suffix = filename.substring(filename.lastIndexOf("."));
            String src = request.getServletContext().getRealPath("file/video/images/");
            File dest = new File(src+ "/" + name + suffix);
            String imgPath = "file/video/images/" + name+suffix;
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result = videoServiceImpl.updVideo(id,name,totalEpisode,currentEpisode,imgPath,type2,category,starring,year,location,statue,description);
        }else{
            result = videoServiceImpl.updVideo(id,name,totalEpisode,currentEpisode,null,type2,category,starring,year,location,statue,description);
        }

        Map<String,Object> map = new HashMap<>();
        if (result == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        return map;
    }

    @RequestMapping("video/updateForForm")
    public String updateForForm(HttpServletRequest request, MultipartFile file,Integer id
            , String name, @RequestParam(value = "type",required = false) Integer type2
            , @RequestParam(value = "category",required = false) List<Integer> category
            , String starring, Integer year, String location,Integer statue
            ,String description,Integer totalEpisode,Integer currentEpisode){

        if (file != null){
            String filename = file.getOriginalFilename();   //获取文件名
            String suffix = filename.substring(filename.lastIndexOf("."));
            String src = request.getServletContext().getRealPath("file/video/images/");
            File dest = new File(src+ "/" + name + suffix);
            String imgPath = "file/video/images/" + name+suffix;
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            videoServiceImpl.updVideo(id,name,totalEpisode,currentEpisode,imgPath,type2,category,starring,year,location,statue,description);
        }else{
            videoServiceImpl.updVideo(id,name,totalEpisode,currentEpisode,null,type2,category,starring,year,location,statue,description);
        }
        List<Video> videos = videoServiceImpl.getAllVideos();
        List<Category> type = categoryServiceImpl.selByPid(0);
        request.setAttribute("videos",videos);
        request.setAttribute("type",type);
        return "manager/videolist";
    }



    @RequestMapping("video/detail/{id}")
    public String detail(HttpServletRequest request,@PathVariable("id")int id){
        Video videoWithCategory = videoServiceImpl.getVideoWithCategory(id);
        List<VideoDetail> detailById = videoServiceImpl.getDetailById(id);
        request.setAttribute("video",videoWithCategory);
        request.setAttribute("episodes",detailById);
        return "manager/video_detail";
    }

    @RequestMapping("video/detail/addVideoDetail/{id}")
    @ResponseBody
    public Map<String,Object> addVideoDetail(HttpServletRequest request,@PathVariable("id")int id,String title,int episode){
        int result = videoServiceImpl.insVideoDetail(id,title,episode);
        Map<String,Object> map = new HashMap<>();
        if (result == 1){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        return map;
    }

//    /**
//     * webUploader上传单个文件
//     * @param file
//     * @return
//     */
//    @RequestMapping("video/detail/upload")
//    @ResponseBody
//    public void upload(HttpServletRequest req, MultipartFile file,String fileMd5,String chunk){
//        File dir = new File(req.getServletContext().getRealPath("file/video/videos/"));
//        File chunkFile = new File(dir + "/" + file.getOriginalFilename());
//        try {
//            FileUtils.copyInputStreamToFile(file.getInputStream(),chunkFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * webUploader分块上传单个文件
     * @param file
     * @return
     */
    @RequestMapping("video/detail/upload")
    @ResponseBody
    public void upload(HttpServletRequest req, MultipartFile file,String fileMd5,String chunk){
        File dir = new File(req.getServletContext().getRealPath("file/video/videos/") +"/"+ fileMd5);
        if (!dir.exists()){
            dir.mkdir();
        }
        File chunkFile = new File(dir + "/" + chunk);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),chunkFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 断点续传，检查分片是否以上传
     * @param chunk
     * @param chunkSize
     * @return
     */
    @RequestMapping("video/detail/checkChunk")
    @ResponseBody
    public Map<String,Object> checkChunk(HttpServletRequest req,String fileMd5,int chunk,String chunkSize){
        File checkFile = new File(req.getServletContext().getRealPath("file/video/videos/") + "/" + fileMd5 + "/" + chunk);
        Map<String,Object> map = new HashMap<>();
        //检查文件大小是否一致
        if (checkFile.exists() && checkFile.length() == Integer.parseInt(chunkSize)){
            map.put("ifExist",1);
        }else {
            map.put("ifExist",0);
        }
        return map;
    }

    /**
     * 合并分片
     * @param fileName
     * @param fileMd5
     * @return
     */
    @RequestMapping(value = "video/detail/mergeChunks",produces="text/html;charset=utf-8")
    @ResponseBody
    public String mergeChunks(HttpServletRequest req, String fileName,String fileMd5){
        String ServerPath = req.getServletContext().getRealPath("file/video/videos/");
        //找到路径中所有的分片文件
        File f = new File(ServerPath + "/" + fileMd5);
        File[] fileArray = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File src) {
                //只筛选文件
                if (src.isDirectory()){
                    return false;
                }
                return true;
            }
        });
        //转成集合，便于排序
        List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));

        //从小到大排序
        fileList.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                    return -1;
                }
                return 1;
            }
        });

        File outputFile = new File(ServerPath + "/" + fileName);

        try {
            //创建文件
            outputFile.createNewFile();
            //输出流
            FileChannel outChannel = new FileOutputStream(outputFile).getChannel();
            //合并
            FileChannel inChannel;
            for (File file : fileList){
                inChannel = new FileInputStream(file).getChannel();
                inChannel.transferTo(0,inChannel.size(),outChannel);
                inChannel.close();

                //删除分片
                file.delete();
            }
            outChannel.close();
            //清除文件夹
            File fileDir = new File(ServerPath + "/" + fileMd5);
            if (fileDir.isDirectory() && fileDir.exists()){
                fileDir.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("合并成功");
        return fileName;
    }

    @RequestMapping("video/detail/updDetailAndDate")
    @ResponseBody
    public Map<String,Object> updDetailAndDate(VideoDetail detail){
        Date date = new Date();
        detail.setUploadDate(date);
        Map<String,Object> map = new HashMap<>();
        map.put("code",videoServiceImpl.updDetailById(detail));
        return map;
    }

    @RequestMapping("video/detail/updDetail")
    @ResponseBody
    public Map<String,Object> updDetail(VideoDetail detail){
        Map<String,Object> map = new HashMap<>();
        map.put("code",videoServiceImpl.updDetailById(detail));
        return map;
    }

    @RequestMapping("video/detail/del")
    @ResponseBody
    public int delDetail(HttpServletRequest request, Integer id){
        VideoDetail detail = videoServiceImpl.getDetailByDetailId(id);
        File path = new File(request.getServletContext().getRealPath("file/video/videos/" + detail.getFileName()));
        if (path.exists()){
            path.delete();
        }
        return videoServiceImpl.delDetailByDetailId(id);
    }
    /**
     * 视频分类分页数据，返回数据给页面json
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value="category/getAll")
    @ResponseBody
    public Map<String,Object> getAllCategory(Integer page,Integer limit){
        Map<String,Object> map = new HashMap<>();
        int start = (page-1)*limit;
        List<Category> categories = categoryServiceImpl.getAll(start,limit);
        int count = categoryServiceImpl.getCount();
        if (categories.size() > 0){
            map.put("code",0);
            map.put("data",categoryServiceImpl.getAll(start,limit));
            map.put("count",categoryServiceImpl.getCount());
        }else {
            map.put("code",1);
        }
        return map;
    }

    /**
     * 根据条件查询分类分页，
     * @param category 分类查询条件
     * @param page 页面
     * @param limit 每页条数
     * @return
     */
    @RequestMapping("category/getByName")
    @ResponseBody
    public Map<String, Object> getByName(Category category,Integer page,Integer limit){
        Map<String,Object> map = new HashMap<>();
        Integer start = (page-1)*limit;
        List<Category> list = categoryServiceImpl.getByName(category,start,limit);
        Integer count = categoryServiceImpl.getCategoryCount(category);
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
     * 删除分类功能
     * @param id
     * @return 0/1 是否删除成功
     */
    @RequestMapping("category/delCategory")
    @ResponseBody
    public Integer delCategory(@PathVariable("id")Integer id){
        int i = categoryServiceImpl.delCategory(id);
        if (i>0){
            //删除分类成功，删除该分类视频分类联合表下的信息。
            videoCategoryServiceImpl.delVideoCategory(id);
        }
        return i;
    }
    /**
     * 根据Id查看单个分类信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("category/detail/{id}")
    public String categoryDetail(@PathVariable("id")Integer id,HttpServletRequest request){
        Category category = categoryServiceImpl.selectByPrimaryKey(id);
        System.out.println(category);
        request.setAttribute("category",category);
        return "manager/user_detail";
    }
//    @RequestMapping("category/list")
//    public String category(){
//        return "manager/categoryList";
//    }
    @RequestMapping("category/list")
    public String category(HttpServletRequest request){
//        List<Category> type = categoryServiceImpl.selectAll();
        List<Category> type = categoryServiceImpl.selByPid(0);
        request.setAttribute("type",type);
//        request.setAttribute("type",type);
        return "manager/categoryList";
    }
    /**
     * 地区分页数据，返回数据给页面json
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value="area/getAll")
    @ResponseBody
    public Map<String,Object> getAreaAll(Integer page,Integer limit){
        int start = (page-1)*limit;
        List<Area> areaList = areaServiceImpl.getAll(start,limit);
        int count = areaServiceImpl.getCount();
        if (areaList.size() > 0){
            result.put("code",0);
            result.put("data",areaList);
            result.put("count",count);
        }else {
            result.put("code",1);
        }
        return result;
    }

    /**
     * 根据条件查询地区分页，
     * @param area 地区查询条件
     * @param page 页面
     * @param limit 每页条数
     * @return
     */
    @RequestMapping("area/getByName")
    @ResponseBody
    public Map<String, Object> getByName(Area area,Integer page,Integer limit){
        Integer start = (page-1)*limit;
        List<Area> list = areaServiceImpl.getByName(area,start,limit);
        Integer count = areaServiceImpl.getCountByName(area);
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
     * 删除地区功能
     * @param id
     * @return 0/1 是否删除成功
     */
    @RequestMapping("area/delArea/{id}")
    @ResponseBody
    public Integer delArea(@PathVariable("id")Integer id){
        int i = areaServiceImpl.deleteByPrimaryKey(id);
//        if (i>0){
//            //删除地区成功，修改该地区下视频的地区状态为空
//            //videoServiceImpl.updateVideoArea(id);
//            //删除视频与地区联合表中该地区的信息。
//            //videoAreaServiceImpl.del(id);
//        }
        return areaServiceImpl.deleteByPrimaryKey(id);
    }
    /**
     * 根据Id查看单个地区信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("area/detail/{id}")
    public String areaDetail(@PathVariable("id")Integer id,HttpServletRequest request){
        Area area = areaServiceImpl.selectByPrimaryKey(id);
        request.setAttribute("area",area);
        return "manager/area_detail";
    }
        @RequestMapping("area/list")
    public String area(){
        return "manager/areaList";
    }

}
