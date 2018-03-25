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
            ,{field: 'role',title: '角色'}

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
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     form.render();
                // }

            }
        });
    });




    $("#searUser").click(function () {
        layer.alert("查询用户");
    });


});







