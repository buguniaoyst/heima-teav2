layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    var userTable = table.render({
        elem: '#stu-table'
        , url: BMY.url.prefix + '/student/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: '序号', sort: true}
            , {
                field: 'studentName',width:300, title: '学生姓名', templet: function (d) {
                    return d.studentName === '' || d.studentName === null ? '' : d.studentName;
                }
            }
            ,{field: 'studentNo',title: '学号',width:100}
            ,{field: 'sex',title: '性别',width:100,templet: function (d) {
                    return d.sex === 1  ? '男' : '女';
                }}
            , {
                field: 'createTime', title: '创建日期',width:200, sort: true, templet: function (d) {
                    return BMY.dateFormatter(d.createTime);
                }
            }
            , {title: '操作', width: 100, align: 'center', toolbar: '#editUserTpl',width:400}
            /* , {title: '状态', width: 90, align: 'center', toolbar: '#enableTpl'}*/
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


    //新增学员
    $("#addStu").click(function () {
        layui.layer.open({
            title : "添加学员",
            type : 2,
            area: ['740px', '380px'], //宽高
            content : "/rest/class_info/stuAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     form.render();
                // }

            }
        });
    });

    //监听工具条
    table.on('tool(stu_info)',function (obj) {
        var data = obj.data;
        if(obj.event === 'edit'){
            layui.layer.open({
                title : "编辑学员信息",
                type : 2,
                area: ['740px', '380px'], //宽高
                content : "/rest/class_info/stuAdd",
                success : function(layero, index){
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#studentName").val(data.studentName);
                    body.contents().find("#studentNo").val(data.studentNo);
                    var sex = data.sex;
                    if(sex === 1){
                        body.contents().find(".sex").get(0).checked = true
                    }else{
                        body.contents().find(".sex").get(1).checked = true
                    }
                    form.render();
                }
            });
        }else if (obj.event === 'del'){
            layer.alert("删除.......");
        }

    });





    $("#searUser").click(function () {
        layer.alert("查询用户");
    });


});







