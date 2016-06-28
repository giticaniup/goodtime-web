var basePath = $("#basePath").val();
var pageNum = 1;

$(document).ready(loadTaskInfo());

function loadTaskInfo() {
    $.ajax({
        type: "get",
        url: basePath + "/diary/getDiary",
        data: {
            pageNum: pageNum
        },
        success: function (data) {
            if (data != null) {
                analyJson(data);
            } else {
                alert("没有权限！");
            }
        }
    });
}
function analyJson(data) {
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            var append = "<div class=\"blog-post\"> <h2 class=\"blog-post-title\">"+data[i].title+"</h2> " +
                "<p class=\"blog-post-meta\">"+data[i].createTime+
                "<p>"+data[i].content+"</p> </div>";
            $("#blogMain").append(append);
        }
    }
}

$("#previousPage").click(function () {
    if (pageNum > 1) pageNum--;
    else pageNum = 1;
});
$("#nextPage").click(function () {
    pageNum++;
});
