<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>轮播图列表</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="static/plugins/layui/css/layui.css"  media="all">
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
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="manager/user/list">用户列表</a></dd>
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
                <li class="layui-nav-item  layui-nav-itemed">
                    <a href="javascript:;">轮播图管理</a>
                    <dl class="layui-nav-child">
                        <dd  class="layui-this"><a href="manager/carousel/list">轮播图列表</a></dd>
                        <dd><a href="manager/carousel/add">上传轮播图</a></dd>
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
            <div class="searchDiv">
                <form>
                    <span style="">搜索栏</span>
                    <label>名称：</label>
                    <input type="text" placeholder="Search">
                    <input type="submit" class="searchBtn" style="" value="搜 索">
                </form>
            </div>

            <div style="border: #beb9b0 solid 1px;margin-top: 20px;border-radius: 5px;">
                <div style="border-bottom: #beb9b0 solid 1px;height:40px;">
                    <h2 style="margin:10px 0 10px 15px;">轮播图列表</h2>
                </div>
                <div style="padding:20px;">
                    <div id="test" lay-filter="test"></div>
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

    //JavaScript代码区域
    layui.use(['element','table','layer'], function(){
        var element = layui.element
            ,table = layui.table
            ,layer = layui.layer;

        table.render({
            elem: '#test'
            ,url:'manager/carousel/getAll'
            ,toolbar: 'true'
            ,cols: [[
                {type:'checkbox', width:60, title: '全选'}
                ,{type:'numbers', width:60, title: '序号'}
                ,{field:'title', title: '名称',minWidth: 60}
                ,{field:'imgSrc',  title: '图片路径',minWidth:80}
                ,{field:'status', width:100, title: '图片状态',templet:function (d) {
                    if (d.status == '0') {
                        return '<div><a href="manager/carousel/updateStatus/'+ d.id +'/' +d.status+'" class="operateBtn">不显示</a></div>'
                    }
                    if (d.status == '1') {
                        return '<div><a href="manager/carousel/updateStatus/'+ d.id +'/' +d.status+'" class="operateBtn">显示</a></div>';
                    }
            }}
                ,{field:'operate',title:'操作'
                    ,templet:function (d) {
                        return '<div><a class="operateBtn" lay-event="delete">删除</a></div>';
                    }
                }
            ]]
            ,page: {
                layout: [ 'count', 'prev', 'page', 'next', 'skip','limit']
                ,limit:10
            }
        });

        $(".searchBtn").click(function () {
            table.reload('test',{
                url:'manager/carousel/getByCondition'
                ,where:{title:$("input:eq(0)").val()}
                ,method:'post'
            });
            return false;
        });

        table.on('tool(test)', function(obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent == 'delete'){
                layer.confirm('确认删除轮播图 \"'+ data.title + '\" ?', {title:'提示'}, function(index){
                    $.get('manager/carousel/delete/'+data.id,function (data) {
                        if (data >= 1){
                            layer.msg("删除成功");
                            obj.del();
                        }else{
                            layer.msg("删除失败");
                        }
                    });
                    layer.close(index);
                });
            }
        })
    });

</script>
</body>
</html>
