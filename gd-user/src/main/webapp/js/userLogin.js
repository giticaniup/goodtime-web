/**
 *
 */
var basePath = $("#basePath").val();
function userLogin() {
    var userId = $('#inputUserId').val();
    var password = $("#inputPassword").val();
    $.post(basePath+"/user/login", JSON.stringify({userId: userId, password: password}),
        function (result) {
            if (result.errorCode == 0 )  window.location.href = basePath+"/task/todoList";
            else {
                $("#userLogin").show();
/*                $('#dd').dialog({
                    display: true,
                    title: '登录失败',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    closable: true,
                    content: '用户名密码错误',
                    modal: true
                });*/
            }
        }
    );
}