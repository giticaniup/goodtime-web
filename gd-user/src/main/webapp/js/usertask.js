/**
 *
 */
var basePath=$("#basePath").val();
$(document).ready(function loadTaskInfo(){
    $.ajax({
        type:"get",
        dataType : 'json',
        url: basePath+"/task/getTask",
        data: ({
            beginTime : null,
            pageSize : null
        }),
        success: function(data) {
            if(data != null) {
                analyJson(data);
            } else {
                alert("没有权限！");
            }
        }
    });
});

function analyJson(data) {
    if(data.length > 0) {
        for(var i=0; i<data.length; i++) {
            var append = "<tr><td>"+data[i].taskId+"</td><td>"+data[i].taskName+"</td><td>"+data[i].taskContent+"</td><td>"+data[i].beginTime+"</td><td>"+data[i].endTime+"</td></tr>";
            $("#tasktBody").append(append);
        }
    }
}