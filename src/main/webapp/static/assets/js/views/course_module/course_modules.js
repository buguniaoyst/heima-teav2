layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    var courseModuleTable = table.render({
        elem: '#course-module-table'
        , url: BMY.url.prefix + '/course_module/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: '序号', sort: true}
            , {
                field: 'moduleName', title: '模块名称', templet: function (d) {
                    return d.moduleName === '' || d.moduleName === null ? '' : d.moduleName;
                }
            }
            ,{field: 'version',title: '版本号'}
            ,{field: 'subjectName',title: '所属学科'}
            ,{field: 'classType',title: '班级类型',templet: function (d) {
                    return d.classType === 1 ? '基础班' : '就业班';
                }}

            , {
                field: 'createTime', title: '创建日期',width:200,sort: true, templet: function (d) {
                    return BMY.dateFormatter(d.createTime);
                }
            }
            ,{field: 'status',title: '状态',templet:function (d) {
                    return d.status === 1 ? '启用' : '禁用';
                }}
            , {title: '操作', width: 270, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });


    table.on('sort(user)', function (obj) {
        courseModuleTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });


    $("#addCourseModule").click(function () {
        layui.layer.open({
            title : "新增课程模块",
            type : 2,
            area: ['640px', '380px'], //宽高
            content : "/rest/course_module/courseModuleAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     form.render();
                // }

            }
        });
    });

    $("#searCourseModule").click(function () {
        layer.alert("搜索课程模块");
    });


});







