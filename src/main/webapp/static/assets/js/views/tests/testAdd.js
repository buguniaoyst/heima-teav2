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
            {type:'checkbox'}
            ,{field: 'id', width: 60, title: '序号', sort: true}
            ,{field: 'itemContent',width:300,title: '题干【单选】'}
            , {title: '操作',  width: 100, align: 'center', toolbar: '#editUserTpl'}
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
            {type:'checkbox'}
            ,{field: 'id', width: 60, title: '序号', sort: true}
            ,{field: 'itemContent',width: 300,title: '题干【多选】'}
            , {title: '操作',  width: 100, align: 'center', toolbar: '#editUserTpl'}
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
            {type:'checkbox'}
            ,{field: 'id', width: 60, title: '序号', sort: true}
            ,{field: 'itemContent',width: 300,title: '题干【编程题】'}
            , {title: '操作', width: 100, align: 'center', toolbar: '#editUserTpl'}
        ]]
    });



})