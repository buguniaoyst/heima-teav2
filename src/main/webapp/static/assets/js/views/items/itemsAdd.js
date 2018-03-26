layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    var singleItemTable = table.render({
        elem: '#single-item-table'
        , url: '/json/items.json'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 45, title: '序号', sort: true}

            ,{field: 'nickname',title: '题干【单选】'}

            , {title: '操作', width: 150, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });


    var multiItemTable = table.render({
        elem: '#multi-item-table'
        , url: '/json/items.json'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 45, title: '序号', sort: true}

            ,{field: 'nickname',title: '题干【多选】'}

            , {title: '操作', width: 150, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });




    var codeItemTable = table.render({
        elem: '#code-item-table'
        , url: '/json/items.json'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 45, title: '序号', sort: true}
            ,{field: 'nickname',title: '题干【编程题】'}

            , {title: '操作', width: 150, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });



    $("#addItems").click(function () {
        var index = layui.layer.open({
            title : "新增题目",
            type : 2,
            content : "/rest/items/itemsAdd",
            success : function(layero, index){
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    });


    /**
     * 监听题型  动态改变答案选项
     */
    form.on('select(itemType)',function (obj) {
        if(obj.value && obj.value === '1'){
            //单选题
            $("#multiItemAnswerArea").addClass("layui-hide");
            $("#codeItemAnswerArea").addClass("layui-hide");
            $("#singleItemAnswerArea").removeClass("layui-hide");
        }else if(obj.value && obj.value === '2'){
            //多选题
            $("#multiItemAnswerArea").removeClass("layui-hide");
            $("#codeItemAnswerArea").addClass("layui-hide");
            $("#singleItemAnswerArea").addClass("layui-hide");
        }else{
            //编程题
            $("#multiItemAnswerArea").addClass("layui-hide");
            $("#codeItemAnswerArea").removeClass("layui-hide");
            $("#singleItemAnswerArea").addClass("layui-hide");

        }


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


    $("#addUser").click(function () {
        layer.alert("新增用户");
    });

    $("#searUser").click(function () {
        layer.alert("查询用户");
    });


});







