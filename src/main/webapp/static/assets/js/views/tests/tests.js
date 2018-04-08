layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;


    var testTable = table.render({
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
        var testType = data.testType;
        var testItemIds = data.testItemIds;
        var testName = data.testName;
        var testSourcesId = data.testSourcesId;
        var testStatus = data.testStatus;
        if(obj.event === 'edit'){
            //编辑
            layer.alert("暂不提供修改方法");

        }else if(obj.event === 'del') {
            //删除

        }else if(obj.event === 'show') {
            //查试卷预览
            var index = layui.layer.open({
                title : "试卷预览",
                type : 2,
                content : "/rest/tests/testDetail",
                success : function(layero, index){
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#testName").html(testName);
                    //发送ajax请求 获取试卷中的题目
                    $.get("/rest/item/getItemsByItemIds",{itemIds:testItemIds},function (result) {
                     if(result !== null && result !== undefined && result !== {}){
                         var count = 0;
                         //创建单选题
                         var singleItems = createSingleItem(count, result.singleItems);
                         body.contents().find("#singleItemArea").html(singleItems);
                         //创建多选题
                         var multiItems = createMultiItem(result.singleItems.length, result.multiItems);
                         body.contents().find("#multiItemArea").html(multiItems);

                          //创建编程题
                         var codeItems = createCodeItem(new Number(result.singleItems.length)+ new Number(result.multiItems.length), result.codeItems);
                         body.contents().find("#codeItemArea").html(codeItems);
                         form.render();
                     }
                    });

                }
            });

            layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            // $(window).on("resize",function(){
            //     layui.layer.full(index);
            // })
            form.render();
        }else if(obj.event === 'assign') {
            //分配试卷
            var index = layui.layer.open({
                title : "分配试卷",
                type : 2,
                area: ['720px', '400px'], //宽高
                content : "/rest/tests/assignTest",
                success : function(layero, index){
                    var body = layui.layer.getChildFrame('body', index);
                    body.contents().find("#testName").val(testName);
                    body.contents().find("#testId").val(data.id);


                }
            });
        }


    });

   function createSingleItem(count, data){
        if(null !== data && [] !== data && undefined !== data){
            var singleItems = "";
            for(var i = 0;i<data.length;i++) {
                count++;
                var itemOptions = data[i].itemAnswerOption.split("$$");
                singleItems+= ' <div class="margin-large bianse" >\n' +
                    '                <h4 style="background-color: #F5F5F5">\n' +
                    '                    <div class="txt-border txt-small radius-big border-blue cusmargin" style=" margin-right:10px">\n' +
                    '                        <div class="txt radius-big bg-blue">'+count+'</div>\n' +
                    '                    </div>\n' +
                    '                    <div style="margin-left: 5%">\n' +data[i].itemContent+
                    '                    </div>\n' +
                    '                </h4>\n' +
                    '                <div style="margin-left: 10px;margin-top: 2%;">\n' +
                    '                    <div >\n' +
                    '                        <input type="radio" name="sex" value="nan" title="'+itemOptions[0]+'">\n' +
                    '                    </div>\n' +
                    '                    <div>\n' +
                    '                        <input type="radio" name="sex" value="nan" title="'+itemOptions[1]+'">\n' +
                    '                    </div>\n' +
                    '                    <div>\n' +
                    '                        <input type="radio" name="sex" value="nan" title="'+itemOptions[2]+'">\n' +
                    '                    </div>\n' +
                    '                    <div>\n' +
                    '                        <input type="radio" name="sex" value="nan" title="'+itemOptions[3]+'">\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>';
            }

            return singleItems;
        }
    };

   function createMultiItem(count,data) {
       if(null !== data && [] !== data && undefined !== data){
           var singleItems = "";
           for(var i = 0;i<data.length;i++) {
               count++;
               var itemOptions = data[i].itemAnswerOption.split("$$");
               singleItems+= ' <div class="margin-large bianse" >\n' +
                   '                <h4 style="background-color: #F5F5F5">\n' +
                   '                    <div class="txt-border txt-small radius-big border-blue cusmargin" style=" margin-right:10px">\n' +
                   '                        <div class="txt radius-big bg-blue">'+count+'</div>\n' +
                   '                    </div>\n' +
                   '                    <div style="margin-left: 5%">\n' +data[i].itemContent+
                   '                    </div>\n' +
                   '                </h4>\n' +
                   '                <div style="margin-left: 10px;margin-top: 2%;">\n' +
                   '                    <div >\n' +
                   '                        <input type="checkbox" lay-skin="primary" name="sex" value="nan" title="'+itemOptions[0]+'">\n' +
                   '                    </div>\n' +
                   '                    <div>\n' +
                   '                        <input type="checkbox" lay-skin="primary" name="sex" value="nan" title="'+itemOptions[1]+'">\n' +
                   '                    </div>\n' +
                   '                    <div>\n' +
                   '                        <input type="checkbox" lay-skin="primary" name="sex" value="nan" title="'+itemOptions[2]+'">\n' +
                   '                    </div>\n' +
                   '                    <div>\n' +
                   '                        <input type="checkbox" lay-skin="primary" name="sex" value="nan" title="'+itemOptions[3]+'">\n' +
                   '                    </div>\n' +
                   '                </div>\n' +
                   '            </div>';
           }

           return singleItems;
       };
   };


    function createCodeItem(count,data) {
        if(null !== data && [] !== data && undefined !== data){
            var singleItems = "";
            for(var i = 0;i<data.length;i++) {
                count++;
                singleItems+= '<div class="margin-large bianse">\n' +
                    '               <h4 style="background-color: #F5F5F5">\n' +
                    '                   <div class="txt-border txt-small radius-big border-blue cusmargin" style=" margin-right:10px">\n' +
                    '                       <div class="txt radius-big bg-blue">'+count+'</div>\n' +
                    '                   </div>\n' +
                    '                   <div style="margin-left: 5%">\n' +data[i].itemContent+
                    '                   </div>\n' +
                    '               </h4>\n' +
                    '               <div style="margin-top: 2%;">\n' +
                    '                   <textarea name="" required lay-verify="required" placeholder="请将你的程序粘贴到此文本域" class="layui-textarea"></textarea>\n' +
                    '               </div>\n' +
                    '           </div>';
            }

            return singleItems;
        };
    };


    /**
     * 监听试卷类型的select
     */
    form.on('select(testTypeId)',function (data) {

        if(data.value === '1'){
            testTable.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    testType: '1'
                }
            });
        }else if(data.value === '2'){
            //过滤查询课后作业卷
            testTable.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    testType: '2'
                }
            });
        }else if(data.value === '3'){
            //过滤查询开班考试试卷
            testTable.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    testType: '3'
                }
            });
        }else {
            //查询所有试卷
            testTable.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    testType: ''
                }
            });
        }


    });


});







