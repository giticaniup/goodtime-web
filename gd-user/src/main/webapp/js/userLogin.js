/**
 *
 */
function userLogin() {
    var userName = $('#username').textbox('getValue');
    var password = $('#password').textbox('getValue');
    $.post("user/userLogin.do", {userName: userName, password: password},
        function (result) {
            if (result == 'true')  window.location.href = "/mySSM/user/getUserInfo?username=" + userName;
            else {
                /*					$('#win').window({
                 title:'登录失败',
                 content:'用户名或密码错误!',
                 closable:true,
                 minimizable:true,
                 width:500,
                 height:300,
                 padding:20,
                 modal:true,
                 });*/
                $('#dd').dialog({
                    display: true,
                    title: '登录失败',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    closable: true,
                    content: '用户名密码错误',
                    modal: true
                });

            }
        }
    );
}