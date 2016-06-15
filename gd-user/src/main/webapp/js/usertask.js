/**
 *
 */
var basePath = $("#basePath").val();

$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});

$("#addTaskButton").click(function () {
    var param = $("#addUserTask").serialize();

    $.ajax({
        type: "post",
        url: basePath + "/task/addUserTask",
        data: param,
        success: function (result) {
            if (result.errorCode == 0) {
                $("#addTask").modal('hide');
                $("#tasktBody").empty();
                loadTaskInfo();
            }else {
                $("#addUserTaskWarning").show();
            }
        },
        error: function (e) {
            alert(e);
        }
    });
});

$(document).ready(loadTaskInfo());

function loadTaskInfo() {
    $.ajax({
        type: "get",
        url: basePath + "/task/getTask",
        data: ({
            beginTime: null,
            endTime: null
        }),
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
            var append = "<tr><td>" + data[i].taskId + "</td><td>" + data[i].taskName + "</td><td>" + data[i].taskContent + "</td><td>" + data[i].beginTime + "</td><td>" + data[i].endTime + "</td></tr>";
            $("#tasktBody").append(append);
        }
    }
}