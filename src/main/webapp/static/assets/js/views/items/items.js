layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
        element.render();

    var singleItemTable = table.render({
        elem: '#single-item-table'
        , url: '/rest/item/list?itemType=1'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 65, title: '序号', sort: true}

            ,{field: 'itemContent',title: '题干【单选】'}

            , {title: '操作', width: 150, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });


    var multiItemTable = table.render({
        elem: '#multi-item-table'
        , url: '/rest/item/list?itemType=2'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 65, title: '序号', sort: true}

            ,{field: 'itemContent',title: '题干【多选】'}

            , {title: '操作', width: 150, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });




    var codeItemTable = table.render({
        elem: '#code-item-table'
        , url: '/rest/item/list?itemType=0'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 65, title: '序号', sort: true}
            ,{field: 'itemContent',title: '题干【编程题】'}

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


    //监听工具条
    table.on('tool(singleItemTable)',function (obj) {
        var data = obj.data;
        if(obj.event === 'edit'){
            var index = layui.layer.open({
                title : "修改单选题",
                type : 2,
                content : "/rest/items/itemsAdd",
                success : function(layero, index){
                    console.log(data);
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#itemContentId").val(data.itemContent);
                    body.contents().find("#itemAnswerId").val(data.itemAnswer);
                    body.contents().find("#itemTypeId").val(data.itemType);
                    body.contents().find("#classTypeId").val(data.classType);
                    body.contents().find("#itemScoreId").val(data.itemScore);
                    body.contents().find("#codeItemAnswerArea").addClass("layui-hide");
                    body.contents().find("#singleItemAnswerArea").removeClass("layui-hide");
                    //给单选选项赋值
                    var itemAnswerOptions = data.itemAnswerOption;
                    var itemAnswer = data.itemAnswer;
                    if(itemAnswerOptions ){
                        var itemAnswerArr = itemAnswerOptions.split("$$");
                        if(itemAnswerArr && itemAnswerArr.length>0) {
                            body.contents().find(".singleItemOption")[0].value = itemAnswerArr[0];
                            body.contents().find(".singleItemOption")[1].value = itemAnswerArr[1];
                            body.contents().find(".singleItemOption")[2].value = itemAnswerArr[2];
                            body.contents().find(".singleItemOption")[3].value = itemAnswerArr[3];
                        }
                    }

                    //填充正确答案
                   if('A' === itemAnswer){
                       body.contents().find(".itemAnswerOption")[0].checked = true;
                   }else if('B' === itemAnswer) {
                       body.contents().find(".itemAnswerOption")[1].checked = true;
                   }else if('C' === itemAnswer) {
                       body.contents().find(".itemAnswerOption")[2].checked = true;
                   }else if('D' === itemAnswer) {
                       body.contents().find(".itemAnswerOption")[3].checked = true;
                   }

                    form.render();
                }
            });
            layui.layer.full(index);
        }else if(obj.event === 'del'){
            layer.alert("删除单选题");
        }

    });

    table.on('tool(multiItemTable)',function (obj) {
        var data = obj.data;
        if(obj.event === 'edit'){
            var index = layui.layer.open({
                title : "修改多选题",
                type : 2,
                content : "/rest/items/itemsAdd",
                success : function(layero, index){
                    console.log(data);
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#itemContentId").val(data.itemContent);
                    body.contents().find("#itemAnswerId").val(data.itemAnswer);
                    body.contents().find("#itemTypeId").val(data.itemType);
                    body.contents().find("#classTypeId").val(data.classType);
                    body.contents().find("#itemScoreId").val(data.itemScore);
                    body.contents().find("#codeItemAnswerArea").addClass("layui-hide");
                    body.contents().find("#multiItemAnswerArea").removeClass("layui-hide");

                    //给单选选项赋值
                    var itemAnswerOptions = data.itemAnswerOption;
                    var itemAnswer = data.itemAnswer;
                    if(itemAnswerOptions ){
                        var itemAnswerArr = itemAnswerOptions.split("$$");
                        if(itemAnswerArr && itemAnswerArr.length>0) {
                            body.contents().find(".multiItemOption")[0].value = itemAnswerArr[0];
                            body.contents().find(".multiItemOption")[1].value = itemAnswerArr[1];
                            body.contents().find(".multiItemOption")[2].value = itemAnswerArr[2];
                            body.contents().find(".multiItemOption")[3].value = itemAnswerArr[3];
                        }
                    }

                    //填充正确答案
                    if(itemAnswer.indexOf('A')>=0){
                        body.contents().find(".multiAnswerOption")[0].checked = true;
                    }
                    if(itemAnswer.indexOf('B')>=0) {
                        body.contents().find(".multiAnswerOption")[1].checked = true;
                    }
                    if(itemAnswer.indexOf('C')>=0) {
                        body.contents().find(".multiAnswerOption")[2].checked = true;
                    }
                    if(itemAnswer.indexOf('D')>=0) {
                        body.contents().find(".multiAnswerOption")[3].checked = true;
                    }
                    form.render();
                }
            });
            layui.layer.full(index);
        }else if(obj.event === 'del'){
            layer.alert("删除多选题");
        }

    });

    table.on('tool(codeItemTable)',function (obj) {
        var data = obj.data;
        if(obj.event === 'edit'){
            var index = layui.layer.open({
                title : "修改编程题",
                type : 2,
                content : "/rest/items/itemsAdd",
                success : function(layero, index){
                    console.log(data);
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#itemContentId").val(data.itemContent);
                    body.contents().find("#itemAnswerId").val(data.itemAnswer);
                    body.contents().find("#itemTypeId").val(data.itemType);
                    body.contents().find("#classTypeId").val(data.classType);
                    body.contents().find("#itemScoreId").val(data.itemScore);
                    form.render();
                }
            });
            layui.layer.full(index);
        }else if(obj.event === 'del'){
            layer.alert("删除编程题");
        }

    });









});







