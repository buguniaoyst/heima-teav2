layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;


    //常规用法
    laydate.render({
        elem: '#classStartDate',
        format:'yyyy/MM/dd'
    });

    //提交stuAdd表单
    form.on('submit(stuAddOrUpdate)', function (data) {
        $.post('/rest/student/addOrUpdate', data.field, function (res,status) {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        return false;
    });





})