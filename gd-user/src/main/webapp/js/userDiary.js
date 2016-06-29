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

    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth();
    for(var i=0;i<10;i++){
        if(month>1) {
            var append = "<button id='test' type='button' class='btn btn-link'>"+year + "年" + (month--) + "月" +"</button>";
            $("#ol_date").append(append);
        }
    }
}
function analyJson(data) {
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            var append = "<div class=\"blog-post\"> <h2 class=\"blog-post-title\">"+data[i].title+"</h2> " +
                "<p class=\"blog-post-meta\">"+data[i].createTime+
                "<p><pre>"+data[i].content+"</pre></p> </div>";
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

$('.btn btn-link').click(function(){
    //ajaxRequest(basePath + "/year/getDiary");
});
