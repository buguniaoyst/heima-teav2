layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    var userTable = table.render({
        elem: '#course-schedule-table'
        , url: BMY.url.prefix + '/course_schedule/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: '序号', sort: true}
            , {
                field: 'className', title: '班级名称', templet: function (d) {
                    return d.className === '' || d.className === null ? '' : d.className;
                }
            }
            ,{field: 'masterName',title: '班主任'}

            , {
                field: 'createTime', title: '创建日期', sort: true, templet: function (d) {
                    return BMY.dateFormatter(d.createTime);
                }
            }
            , {title: '操作', width: 270, align: 'center', toolbar: '#editUserTpl'}
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


    $("#addCourseSchedule").click(function () {
        layui.layer.open({
            title : "新增课表",
            type : 2,
            area: ['640px', '580px'], //宽高
            content : "/rest/course_sechedule/courseScheduleAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     form.render();
                // }

            }
        });
    });

    $("#searCourseModule").click(function () {
        layer.alert("查询课表");
    });




});







