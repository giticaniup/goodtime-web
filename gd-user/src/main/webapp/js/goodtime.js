function showMessage(message) {
    alert(message);
}

function showErrorMessage(message) {
    message=(message==null||message=="")?"系统异常":message;
    showMessage(message);
}
function ajaxRequest(url,method,data,callback){
    $.ajax({
        type:method,
        url:url,
        data:data,
        success:callback,
        error:function(data){
            showErrorMessage(data.responseText);
        }
    })
}
function ajaxJsonRequest(url, method, data, callback){
    $.ajax({
        type: method,
        url:url,
        data:JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success:callback,
        error:function(data){
            showErrorMessage(data.responseText);
        }});
}

function ajaxJsonAsyncPost(url, data, callback){
    ajaxJsonRequest(url,"post",data,callback);
}

function ajaxJsonAsyncPut(url, data, callback){
    ajaxJsonRequest(url,"put",data,callback);
}

function ajaxJsonAsyncGet(url, data, callback){
    ajaxJsonRequest(url,"get",data,callback);
}

function ajaxJsonAsyncDel(url, data, callback){
    ajaxJsonRequest(url,"delete",data,callback);
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