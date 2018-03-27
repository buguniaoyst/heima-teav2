layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    var userTable = table.render({
        elem: '#test-source-table'
        , url: BMY.url.prefix + '/test_source/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: '序号', sort: true}
            , {
                field: 'testName', title: '试卷名称', templet: function (d) {
                    return d.testName === '' || d.testName === null ? '' : d.testName;
                }
            }
            ,{field: 'testType',title: '适用班级', templet:function (d) {
                    return d.testType === 1 ? '基础班' : '就业班';
                }}
            ,{field: 'testSourcesId',title: '关联课程'}

            , {
                field: 'createTime', title: '创建日期', sort: true, templet: function (d) {
                    return BMY.dateFormatter(d.createTime);
                }
            }
            , {title: '操作', width: 300, align: 'center', toolbar: '#editUserTpl'}
            /*, {title: '状态', width: 90, align: 'center', toolbar: '#enableTpl'}*/
        ]]
    });





    /**
     * 新增试卷
     */
    $("#addTest").click(function () {
        var index = layui.layer.open({
            title : "组卷管理",
            type : 2,
            content : "/rest/tests/testAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     form.render();
                // }
            }
        });
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    });


    /**
     * 分配试卷
     */
    $("#assignTest").click(function () {
        var index = layui.layer.open({
            title : "组卷管理",
            type : 2,
            content : "/rest/tests/testAdd",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     form.render();
                // }
            }
        });
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    });




    $("#searUser").click(function () {
        layer.alert("查询试卷");
    });


    //监听工具栏
    table.on('tool(testSourceTable)',function (obj) {
        var data = obj.data;
        if(obj.event === 'edit'){
            //编辑
            layer.alert("暂不提供修改方法");

        }else if(obj.event === 'del') {
            //删除

        }else if(obj.event === 'show') {
            //查看详情

        }else if(obj.event === 'assign') {
            //分配试卷

        }


    });






});







