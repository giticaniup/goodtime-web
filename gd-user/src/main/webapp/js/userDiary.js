var basePath = $("#basePath").val();
var domYear = $("#year").val();
var domMonth = $("#month").val();

var pageNum = 1;
var pageCount;

$(document).ready(
    loadTaskInfo(), addDateSelector());

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
                pageCount = data.pageCount;
                analyJson(data.userDiaries);
            } else {
                alert("没有权限！");
            }
        }
    });

    /*控制按钮状态*/
    if (pageNum == 1) {
        $("#previousPage").attr('disabled', "true");
    }
    if (pageNum == pageCount) {
        $("#nextPage").attr('disabled', "true");
    }
}
function analyJson(data) {
    $("#blogMain").empty();
    if (data.length > 0) {
        for (var i = 0; i < data.length; i++) {
            var append = "<div class=\"blog-post\"> <h2 class=\"blog-post-title\">" + data[i].title + "</h2> " +
                "<p class=\"blog-post-meta\">" + data[i].createTime +
                "<p><pre>" + data[i].content + "</pre></p> </div>";
            $("#blogMain").append(append);
        }
    }
}

function addDateSelector() {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    for (var i = 0; i < 10; i++) {
        if (month > 1) {
            var append = "<li><a href='" + basePath + "/diary/diaryList/" + year + "/" + month + "'>" + year + "年" + (month--) + "月" + "</a></li>";
            $("#ol_date").append(append);
        }
    }
}
$("#previousPage").click(function () {
    if (pageNum > 1) pageNum--;
    else pageNum = 1;
    $("#nextPage").removeAttr("disabled");
    loadTaskInfo();
});
$("#nextPage").click(function () {
    if (pageNum >= pageCount) {
        $("#nextPage").attr('disabled', "true");
        $("#end_page_warning").show();
        return;
    }
    pageNum++;
    $("#previousPage").removeAttr("disabled");
    loadTaskInfo();
});
