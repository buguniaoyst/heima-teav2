layui.use(['form', 'layer', 'layedit', 'laydate', 'upload','table'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        table = layui.table,
        $ = layui.jquery;


    //提交新增表单
    form.on('submit(addUser)', function (data) {
        $.post('/rest/user/addOrUpdate', data.field, function (res,status) {

            console.log(res);
            console.log(status);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

        return false;
    });

})