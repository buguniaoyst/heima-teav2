layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        ,$ = layui.jquery
        , form = layui.form;
    element.render();

    /**
     * 初始化单选题
     */
    var singleItemTable = table.render({
        elem: '#single-item-table'
        , url: '/rest/item/list?itemType=1'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {type:'checkbox'}
            ,{field: 'id', width: 60, title: '序号', sort: true}
            ,{field: 'itemContent',width:300,title: '题干【单选】'}
            , {title: '操作',  width: 100, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });


    /**
     * 初始化多选题
     */
    var multiItemTable = table.render({
        elem: '#multi-item-table'
        , url: '/rest/item/list?itemType=2'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {type:'checkbox'}
            ,{field: 'id', width: 60, title: '序号', sort: true}
            ,{field: 'itemContent',width: 300,title: '题干【多选】'}
            , {title: '操作',  width: 100, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });


    /**
     * 初始化代码题
     */
    var codeItemTable = table.render({
        elem: '#code-item-table'
        , url: '/rest/item/list?itemType=0'
        , page: true
        , limit: 15
        , height: 'full'
        , cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {type:'checkbox'}
            ,{field: 'id', width: 60, title: '序号', sort: true}
            ,{field: 'itemContent',width: 300,title: '题干【编程题】'}
            , {title: '操作', width: 100, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });


    /**
     * 监听单选题是否被选中
     */
    table.on('checkbox(single-item-table)', function(obj){
        console.log(obj.data); //选中行的相关数据
        if(obj.checked) {
            itemIdsArr.push(obj.data.id);
        }else{
            itemIdsArr.remove(obj.data.id);
        }

        console.log(itemIdsArr);

    });


    /**
     * 监听多选题是否被选中
     */
    table.on('checkbox(multi-item-table)', function(obj){
        if(obj.checked) {
            itemIdsArr.push(obj.data.id);
        }else{
            itemIdsArr.remove(obj.data.id);
        }
    });


    /**
     * 监听代码题是否被选中
     */
    table.on('checkbox(code-item-table)', function(obj){
        if(obj.checked) {
            itemIdsArr.push(obj.data.id);
        }else{
            itemIdsArr.remove(obj.data.id);
        }
    });


    var itemIdsArr = new Array();


    //提交新增表单
    form.on('submit(createTest)', function (data) {
        itemIdsArr = itemIdsArr.join(",");
        data.field.testItemIds = itemIdsArr;
        $.post('/rest/test_source/addOrUpdate', data.field, function (res,status) {
            console.log(res);
            console.log(status);
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            parent.location.reload();
        });

        return false;
    });



});