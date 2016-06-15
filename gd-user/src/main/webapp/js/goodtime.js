
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