layui.use(['jquery', 'layer', 'table', 'laydate'], function () {

    var $ = layui.jquery;
    var laydate = layui.laydate;
    var table = layui.table;

    var SelectDealerDlg = {
        index: parent.layer.getFrameIndex(window.name)
    };

    var SelectDealerTable = {
        tableId: "selectDealerTable"
    };

    SelectDealerTable.initColumn = function () {
        return [[
            {type: 'numbers', title: "No"},
            {field: "account", title: "账号"},
            {field: "name", title: "姓名"},
            {field: "deptName", title: "部门"}
        ]]
    };

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    /**
     * 点击查询按钮
     */
    SelectDealerTable.search = function () {
        var queryData = {};
        queryData['deptId'] = "";
        queryData['name'] = $("#name").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        table.reload(SelectDealerTable.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SelectDealerTable.search();
    });

    table.render({
        elem: '#' + SelectDealerTable.tableId,
        url: Feng.ctxPath + '/mgr/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: SelectDealerTable.initColumn()
    });

    table.on('rowDouble(selectDealerTable)', function (obj) {
        parent.Handle.nextObject = obj.data.name;
        parent.Handle.assignee = obj.data.account;
        parent.layer.close(SelectDealerDlg.index);
    });

});