layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    var userTable = table.render({
        elem: '#user-table'
        , url: BMY.url.prefix + '/user/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: '序号', sort: true}
            , {
                field: 'userName', title: '用户名', templet: function (d) {
                    return d.userName === '' || d.userName === null ? '' : d.userName;
                }
            }
            ,{field: 'role',title: '角色',templet:function (d) {
                    if(d.role === '1'){
                        return '管理员';
                    }else if(d.role === '2') {
                        return '助教';
                    }else{
                        return '游客';
                    }
                }}

            , {
                field: 'createTime', title: '创建日期', sort: true, templet: function (d) {
                    return BMY.dateFormatter(d.createTime);
                }
            }
            , {title: '操作', width: 270, align: 'center', toolbar: '#editUserTpl'}
            /*, {title: '状态', width: 90, align: 'center', toolbar: '#enableTpl'}*/
        ]]
    });




    form.on('switch(enable)', function (obj) {
        BMY.ajax(BMY.url.prefix + "/user/edit/enable", {id: this.value, enable: obj.elem.checked}, function (json) {
            BMY.okMsgHandle(json);
            layer.tips('用户状态：' + ((obj.elem.checked) ? "正常" : "锁定"), obj.othis);
        });
    });

    table.on('sort(user)', function (obj) {
        userTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });


    /**
     * 新增用户
     */
    $("#addUser").click(function () {
         layui.layer.open({
            title : "新增用户",
            type : 2,
            area: ['640px', '380px'], //宽高
            content : "/rest/user/userAdd",
             end: function () {
                 location.reload();
             },
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     form.render();
                // }

            }
        });
    });

    //监听工具条
    table.on('tool(myUserTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
        } else if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
                $.ajax({
                    url:"/rest/user?ids="+data.id
                    ,type:"DELETE"
                    ,success:function(result){
                        // layer.alert("删除成功!");
                    }});

            });
        } else if(obj.event === 'edit'){
//            layer.alert('编辑行：<br>'+ JSON.stringify(data))
            layui.layer.open({
                title: "编辑用户",
                type: 2,
                area: ['640px', '380px'], //宽高
                content: "/rest/user/userAdd",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#userName").val(data.userName);
                    body.contents().find("#userPassword").val(data.password);
                    body.contents().find("#userId").val(data.id);
                    body.contents().find("#userRole").val( data.role);
                    form.render();
                },end: function () {
                    location.reload();
                }
            });
        }
    });


    $("#searUser").click(function () {
        layer.alert("查询用户");
    });

});







