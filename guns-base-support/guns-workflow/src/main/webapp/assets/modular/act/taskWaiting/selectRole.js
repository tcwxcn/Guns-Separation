layui.use(['jquery', 'table'], function () {

    var $ = layui.jquery;
    var table = layui.table;

    var SelectRoleDlg = {
        index: parent.layer.getFrameIndex(window.name)
    };

    var SelectRoleTable = {
        tableId: 'selectRoleTable'
    };

    SelectRoleTable.initColumn = function () {
        return [[
            {type: 'numbers', title: 'No'},
            {field: "id", hide:true, title: 'id'},
            {field: "name", title: '角色名'},
            {field: "description", title: '描述'}
        ]]
    };

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SelectRoleTable.search();
    });

    /**
     * 点击查询按钮
     */
    SelectRoleTable.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        table.reload(SelectRoleTable.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    table.render({
        elem: '#' + SelectRoleTable.tableId,
        url: Feng.ctxPath + '/role/listRole',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: SelectRoleTable.initColumn()
    });

    table.on('rowDouble(selectRoleTable)', function (obj) {
        parent.Handle.nextObject = obj.data.name;
        parent.Handle.assignee = obj.data.id;
        parent.layer.close(SelectRoleDlg.index);
    });
});