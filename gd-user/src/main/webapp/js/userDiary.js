var basePath = $("#basePath").val();
var domYear = $("#year").val();
var domMonth = $("#month").val();

var pageNum = 1;

$(document).ready(loadTaskInfo());

function loadTaskInfo() {
    var url;
    if (domYear == null || domYear == "" || domMonth == null || domMonth == "") {
        url = basePath + "/diary/";
    } else {
        url = basePath + "/diary/" + domYear + "/" + domMonth;
    }

    $.ajax({
        type: "get",
        url: url,
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
    var month = today.getMonth()+1;
    for (var i = 0; i < 10; i++) {
        if (month > 1) {
            var append = "<li><a href='" + basePath + "/diary/diaryList/" + year + "/" + month + "'>" + year + "年" + (month--) + "月" + "</a></li>";
            $("#ol_date").append(append);
        }
    }
}
function analyJson(data) {
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            var append = "<div class=\"blog-post\"> <h2 class=\"blog-post-title\">" + data[i].title + "</h2> " +
                "<p class=\"blog-post-meta\">" + data[i].createTime +
                "<p><pre>" + data[i].content + "</pre></p> </div>";
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
