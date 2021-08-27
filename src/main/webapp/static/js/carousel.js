/**
 * 获取轮播图并展示
 * @param uri
 */
function showCarousel(uri){
    var carouselDiv = $(".carouselDiv");
    var carouselHtml = "";
    $.get(uri, function (data) {
        var jsonOBJ = eval("("+data+")");
        if (jsonOBJ.length != 0) {
            for (var i = 0; i < jsonOBJ.length; i++) {
                carouselHtml += "<div><a href=\"javascript:\">" +
                    "<img src= \"" + jsonOBJ[i].imgSrc + "\" width=\"100%\" height=\"400px\" alt=\"" + jsonOBJ[i].title + "\"></a></div>";
            }
            carouselDiv.html(carouselHtml);
        }
    });
}