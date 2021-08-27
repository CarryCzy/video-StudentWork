/**
 * 获取所有频道并展示
 */
function showPd(uri){
    var pdList = $("#select1");
    var pdHtml = " <span>频道：</span> <li style=\"background: #4EBBF9\"><a href=\"javascript:void (0);\" style=\"color: #fff;\" typeId='0'>全部</a></li> ";
    $.get(uri, function (data) {
        var jsonOBJ = eval("("+data+")");
        for (var i = 0; i < jsonOBJ.length; i++) {
            pdHtml += " <li><a href=\"javascript:void (0);\" typeId='"+ jsonOBJ[i].id +"'>" + jsonOBJ[i].categoryName + "</a></li> ";
        }
        pdList.html(pdHtml);
    });
}
/**
 * 获取所有地区并展示
 */
function showArea(uri){
    var areaList = $("#select3");
    var areaHtml = " <span>地区：</span> <li style=\"background: #4EBBF9\"><a href=\"javascript:void (0);\" style=\"color: #fff;\">全部</a></li> ";
    $.get(uri, function (data) {
        var jsonOBJ = eval("("+data+")");
        for (var i = 0; i < jsonOBJ.length; i++) {
            areaHtml += " <li><a href=\"javascript:void (0);\" >" + jsonOBJ[i].name + "</a></li> ";
        }
        areaList.html(areaHtml);
    });
}