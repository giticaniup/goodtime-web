/**
 *
 */
var basePath=$("#basePath").val();

$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});

$("#addTaskButton").click(function(){
    var param=$("#addUserTask").serializeObject();

    $.ajax({
        type:"post",
        dataType:'json',
        url:basePath+"addUserTask",
        data:JSON.stringify(param),
        contentType: "application/json; charset=utf-8",
        success:function(){
            $("#addTask").modal('hide');
            loadTaskInfo();
        },
        error:function(e){
            alert(e);
        }
    });
});

$(document).ready(loadTaskInfo());

function loadTaskInfo(){
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
}
function analyJson(data) {
    if(data.length > 0) {
        for(var i=0; i<data.length; i++) {
            var append = "<tr><td>"+data[i].taskId+"</td><td>"+data[i].taskName+"</td><td>"+data[i].taskContent+"</td><td>"+data[i].beginTime+"</td><td>"+data[i].endTime+"</td></tr>";
            $("#tasktBody").append(append);
        }
    }
}
//将表单数据封装成对象，各个控件的name为属性名，value为属性值
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        var val = (this.value || '').replace(/\"/g , '');
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(val);
        } else {
            o[this.name] = val;
        }
    });
    return o;
};