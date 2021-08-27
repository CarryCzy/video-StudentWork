/**
 * 获取轮播图并展示
 * @param uri
 */
function showCarousel(uri){
    var carouselDiv = $(".carouselDiv");
    var carousels;
    var carouselHtml = "";
    $.get(uri, function (data) {
        carousels = data.list;
        if (carousels.length != 0) {
            for (var i = 0; i < carousels.length; i++) {
                carouselHtml += "<div><a href=\"javascript:\">" +
                    "<img src= \"" + carousels[i].imgSrc + "\" width=\"100%\" height=\"400px\" alt=\"" + carousels[i].title + "\"></a></div>";
            }
            // alert(carouselHtml);
            carouselDiv.html(carouselHtml);
        }
    });
}