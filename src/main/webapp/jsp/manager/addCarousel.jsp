<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>添加轮播图</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="static/plugins/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="static/css/movieglobal.css">
    <link rel="stylesheet" href="static/css/register.css">
    <script src="static/plugins/layui/layui.js"></script>
    <script src="static/plugins/jquery/jquery.js"></script>
    <style type="text/css">
        .title{line-height: 60px;color: #d0cbc1;margin-left: 20px;}
        .searchDiv{height: 50px;background: #e0e0e0;font-size: 20px;color: #696969;line-height: 50px;border-radius: 5px;}
        .searchDiv span{margin-left:10px;}
        .searchDiv label{display: inline-block;vertical-align: middle;padding-left: 8px; margin-left: 20px;text-align: center; height: 30px;line-height: 30px;box-sizing:border-box;border: #95918a solid 1px;border-radius: 5px; font-size: 15px;color: black;background: #f8f8f8;}
        .searchDiv input{padding-left: 12px; vertical-align: middle;height: 30px;border: #95918a solid 1px;border-radius: 5px;font-size: 15px;color: black;background: #f8f8f8;box-sizing:border-box;}
        .searchDiv input:last-child{vertical-align: middle;height: 30px;font-size: 15px;width: 80px;color: #fff;margin-left: 15px; border-radius: 5px;background: #4EBBF9;border: #d9d4c9 1px solid;padding-left: 0;cursor: pointer;}

        .operateBtn{color: #1d1d1d; margin-right: 20px;width: 50px;box-sizing: border-box; background: #fff;padding: 5px 10px;border: #c4bfb5 solid 1px;border-radius: 5px;cursor: pointer;}
        .operateBtn:hover{color: #1d1d1d}
        .operateBtn:last-child{background: #ff5833;color: #fff}
        .operateBtn:last-child:hover{color: #fff}
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">想看视频后台管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <a class="layui-layout-left title" style="">
            用户列表
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <c:if test="${sessionScope.admin == null}">
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                        未登录
                    </c:if>
                    <c:if test="${sessionScope.admin != null}">
                        <img src="${sessionScope.admin.img}" class="layui-nav-img">
                        ${sessionScope.admin.name}
                    </c:if>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a href="jsp/manager/index.jsp">首页</a>
                </li>
                <li class="layui-nav-item layui-nav-itemed" onclick="">
                    <a class="" href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">用户列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">视频管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="manager/video/list">视频列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">视频分类管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="manager/category/list">视频分类列表</a></dd>
                        <dd><a href="manager/category/add">添加分类</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">演员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="manager/actor/list">演员列表</a></dd>
                        <dd><a href="manager/actor/add">添加演员</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">地区管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="manager/area/list">地区列表</a></dd>
                        <dd><a href="manager/area/add">添加地区</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">轮播图管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="manager/carousel/list">轮播图列表</a></dd>
                        <dd  class="layui-this"><a href="manager/carousel/add">上传轮播图</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">评论管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="manager/comment/list">评论列表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="register-content">
                <div class="register-container">
                    <form action="manager/carousel/addCarousel" method="post" >
                        <div class="register-input-line">
                            <span>轮播图名称：</span>
                            <input type="text" class="register-input" name="title" placeholder="请输入轮播图名称">
                            <label class="register-input-error"></label>
                        </div>
                        <div class="register-input-line">
                            <span>上传轮播图：</span>
                            <input type="hidden" name="imgSrc" value="">
                            <div class="layui-upload-drag" style="float: left" id="upload">
                                <i class="layui-icon"></i>
                                <p>点击上传，或将图片拖拽到此处</p>
                            </div>
                            <div class="register-image-div"></div>
                        </div>
                        <div class="register-input-submit-line">
                            <input type="submit" class="register-input-submit-btn" onclick="return submitClick()" value="上传">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(".layui-nav-itemed").trigger('mouseenter');
    $(".layui-nav-item").click(function () {
        $(".layui-nav-item").not(this).removeClass('layui-nav-itemed');
        $(".layui-nav-bar").css({top: $(this).position().top+'px'})
    });
    layui.use(['element'], function() {
        var element = layui.element
    });
    //轮播图名称验证
    var title = $(".register-input:eq(0)");
    var titleError = $(".register-input-error:eq(0)");
    title.blur(function () {
        titleVerify();
    });
    function titleVerify() {
        if (title.val() == "" || title.val()==null){
            nameError.css("color","red").html("名称不能为空！");
            return false;
        }
    }
    //JavaScript代码区域
    //控制表单提交
    function submitClick() {
        var titleFlag = titleVerify();
        var flag = titleFlag;
        return  flag;
    }
    /**
     * 上传头像
     */
    //拖拽上传
    layui.use(["layer",'upload'],function () {
        var layer = layui.layer
            ,upload = layui.upload;
        /**
         * 上传头像
         */
        //拖拽上传
        upload.render({
            elem: '#upload'
            ,url: 'manager/carousel/imageUpload'
            ,done: function(result){
                if (result.code == 0) {//上传失败
                    return layer.msg('上传失败');
                }else if(result.code == 1){
                    $(".register-image-div").css({background:"url("+result.src+") no-repeat center","background-size":"100% 100%"});
                    $("input[name='imgSrc']").val(result.src);
                }}});

    });
</script>
</body>
</html>
