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
        });
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



    //监听新增题目提交按钮
    form.on('submit(addItem)', function (data) {
        console.log(data.field);
      //根据题型 封装数据
        var itemType = data.field.itemType;
         if('1' === itemType) {
            //封装单选题
             var optionA = data.field.sItemAnswerA;
             var optionB = data.field.sItemAnswerB;
             var optionC = data.field.sItemAnswerC;
             var optionD = data.field.sItemAnswerD;
             data.field.itemAnswerOption = optionA+"$$"+optionB+"$$"+optionC+"$$"+optionD;
        }else if('2' === itemType) {
            //封装多选题  itemAnswerD

             var optionA = data.field.mItemAnswerA;
             var optionB = data.field.mItemAnswerB;
             var optionC = data.field.mItemAnswerC;
             var optionD = data.field.mItemAnswerD;
             data.field.itemAnswerOption = optionA+"$$"+optionB+"$$"+optionC+"$$"+optionD;

             var answerA = data.field.itemAnswerA;
             var answerB = data.field.itemAnswerB;
             var answerC = data.field.itemAnswerC;
             var answerD = data.field.itemAnswerD;
             data.field.itemAnswer = answerA+"$$"+answerB+"$$"+answerC+"$$"+answerD;
        }

        $.post('/rest/item/itemAddOrUpdate', data.field, function (res,status) {
            console.log(res);
            console.log(status);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            parent.location.reload();
        });

        return false;

    });




});







