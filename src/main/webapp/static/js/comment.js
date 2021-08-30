/*后台返回的评论分页信息*/
var pageInfo;

/**
 *获取评论信息并展示
 */
function showComment(uri,pageNum,pageSize) {
    var commentResult = '';
    var commentDiv = $(".commentDiv");
    var comments;

    $.get(uri +"/"+ pageNum +"/"+pageSize, function (data) {
        pageInfo = data;
        comments = data.list;

        if (comments.length == 0) {
            commentResult = "<div style='width:100%; height:300px;text-align: center; '><h1 style='color: #777777;line-height: 300px;'>抱歉，没有评论哦！</h1></div>";
            commentDiv.html(videoResult);
        }else{
            for (var i = 0; i < comments.length; i++) {
                var comment = " <li>\n" + " <p>评论：" + comments[i].comment + "</p>\n" + " </li>";
            }
            commentResult += "</ul>\n<ul>\n" + comment;
        }
        commentDiv.html(commentResult);
        /*layui分页按钮*/
        layui.use(['laypage'], function() {
            var layPage = layui.laypage;
            layPage.render({
                elem: 'pagebtn'
                , count: pageInfo.count
                , limit: pageInfo.pageSize
                , curr: pageInfo.pageNum
                , layout: ['count', 'prev', 'page', 'next', 'skip']
                , hash: 'skip'
                , jump: function (obj, first) {
                    if (!first) {
                        pageInfo.pageNum = obj.curr;
                        showVideo(uri,obj.curr,pageInfo.pageSize);
                        location.href = '#skip';
                    }
                }
            });
        });
    });
}
