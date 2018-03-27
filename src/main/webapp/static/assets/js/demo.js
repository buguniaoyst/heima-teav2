layui.use(['form', 'layer'], function () {
    $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer;

//自定义验证规则
    form.verify({
        nikename: function (value) {
            if (value.length < 5) {
                return '昵称至少得5个字符啊';
            }
        }
        , pass: [/(.+){6,12}$/, '密码必须6到12位']
        , repass: function (value) {
            if ($('#L_pass').val() != $('#L_repass').val()) {
                return '两次密码不一致';
            }
        }
    });

    以上只是框架加载部分

//监听提交
    form.on('submit(add)', function (data) {

        $.ajax({
            url: "http://xxxxxxxxxxxxxx/api/User/AddUser",
//dataType: 'text',
            contentType: "application/json",
            data: user, //请求的附加参数，用json对象
            method: 'POST',
            success: function (res) {


            },
            error: function () {

            }
        });
        return false;//只此一句
    });
});