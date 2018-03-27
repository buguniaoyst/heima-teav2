layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;


    //提交新增表单
    form.on('submit(addUser)', function (data) {
        // $.post('/rest/user/addOrUpdate', data.field, function (res,status) {
        //
        //     console.log(res);
        //     console.log(status);
        //
        // //res就是返回的结果
        // });
        // return false;
        $.ajax({
            type: "post"
            , url: BMY.url.prefix + "/user/addOrUpdate"
            , dataType: "json"
            , data: data.field
            , success: function (res,status) {
                console.log(res);
                console.log(status);
                table.reload('userListTable', {
                    url: '/rest/user/list'
                });
                layer.close(index);
            }

        });
        return false;
    });

})