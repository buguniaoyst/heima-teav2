layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    var userTable = table.render({
        elem: '#class-table'
        , url: BMY.url.prefix + '/class/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: '序号', sort: true}
            , {
                field: 'className',width:300, title: '班级名称', templet: function (d) {
                    return d.className === '' || d.className === null ? '' : d.className;
                }
            }
            ,{field: 'classType',title: '班级类型',width:100,templet: function (d) {
                    return d.classType === 1  ? '基础班' : '就业班';
                }}
            ,{field: 'masterName',title: '班主任',width:100}
            ,{field: 'assistant',title: '助教',width:100}

            , {
                field: 'createTime', title: '创建日期', sort: true, templet: function (d) {
                    return BMY.dateFormatter(d.createTime);
                }
            }
            , {title: '操作', width: 330, align: 'center', toolbar: '#editUserTpl'}
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


    $("#addClass").click(function () {
        layui.layer.open({
            title : "新增班级",
            type : 2,
            area: ['740px', '480px'], //宽高
            content : "/rest/class_info/classAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
            },
            end:function () {
                location.reload();
            }
        });
    });

    $("#searClass").click(function () {
        layer.alert("查询用户");
    });

    table.on('tool(class_info)',function(obj){
        var data = obj.data;
        if(obj.event === 'edit'){
            layui.layer.open({
                title: "编辑用户",
                type: 2,
                area: ['640px', '380px'], //宽高
                content: "/rest/class_info/classAdd",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#className").val(data.className);
                    body.contents().find("#masterName").val(data.masterName);
                    body.contents().find("#classId").val(data.id);
                    body.contents().find("#classStartDate").val(new Date().format("yyyy/MM/dd"));
                    body.contents().find("#classType").val(data.classType);
                    form.render();
                },
                end:function () {
                    location.reload();
                }
        });
        }else if(obj.event === 'del'){
           layer.confirm('真的删除行么', function(index){
           obj.del();
           layer.close(index);
               $.ajax({
                   url:"/rest/class?id="+data.id
                   ,type:"DELETE"
                   ,success:function(result){
                       // layer.alert("删除成功!");
                   }});
         });
        }else if(obj.event === 'show') {
            //查看班级中的学生信息
            var index = layui.layer.open({
                title : "学员信息",
                type : 2,
                content : "/rest/class_info/stu_info",
                success : function(layero, index){
                    //将classId 添加到页面隐藏域中
                    var body = layui.layer.getChildFrame('body', index);
                    body.find("#stuClassId").val(data.id);
                    form.render();
                }
            })
            layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize",function(){
                layui.layer.full(index);
            })
        }else if(obj.event == 'upGrade'){
            if(data.classType === 2){
                layer.alert("只有基础班才能升级！");
                return;
            }
            layui.layer.open({
                title: "基础班升级",
                type: 2,
                area: ['640px', '380px'], //宽高
                content: "/rest/class_info/classAdd",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var className = data.className.replace("基础", "就业");
                    body.contents().find("#className").val(className);
                    body.contents().find("#className").attr("readonly", true);
                    body.contents().find("#masterName").val(data.masterName);
                    body.contents().find("#classId").val(data.id);
                    body.contents().find("#classStartDate").val(new Date().format("yyyy/MM/dd"));
                    body.contents().find("#classType").val(2);
                    body.contents().find("#modifyType").val(2);
                    body.contents().find("#classType").attr("readonly", true);
                    form.render();
                },
                end:function () {
                    location.reload();
                }
            });
        }

    })






});







