package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.*;
import com.example.service.AdminsService;
import com.example.service.VideoService;
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

    @RequestMapping("toIndex")
    public String toIndex(){
        return "redirect:/jsp/manager/index.jsp";
    }


    @RequestMapping("user/list")
    public String user(){
        return "manager/userlist";
    }



    @RequestMapping("video/list")
    public String video(HttpServletRequest request){
        List<Video> videos = videoServiceImpl.getAllVideos();
//        List<Category> type = categoryServiceImpl.selByPid(0);
        request.setAttribute("videos",videos);
//        request.setAttribute("type",type);
            return "manager/videolist";
    }


    @RequestMapping("video/search")
    public String search(HttpServletRequest request,Video video,String year){
        System.out.println(video);
        List<Video> videos = videoServiceImpl.getAllVideosByCondition(video,year);
//        List<Category> types = categoryServiceImpl.selByPid(0);
        request.setAttribute("videos",videos);
//        request.setAttribute("type",types);
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
//        List<Category> type = categoryServiceImpl.selByPid(0);
        request.setAttribute("videos",videos);
//        request.setAttribute("type",type);
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




}
