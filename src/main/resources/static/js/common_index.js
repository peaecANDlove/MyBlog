// 导航
$('.menu.toggle').click(function() {
    $('.m-item').toggleClass('m-mobile-hide');
});




//表单验证
$('.ui.login.form').form({
    on: 'blur',
    fields : {
        username:{
            identifier: 'username',
            rules: [
                {
                    type: 'empty',
                    prompt: "账号不能为空"
                }
            ]
        },
        password:{
            identifier: 'password',
            rules:[
                {
                    type: 'empty',
                    prompt: "密码不能为空"
                }
            ]
        }
    }

});


// 数据获取

//博客主体信息获取
