layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 租户表管理
     */
    var TenantInfo = {
        tableId: "tenantInfoTable"
    };

    /**
     * 初始化表格的列
     */
    TenantInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'tenantId', hide: true, align: "center", title: '主键id'},
            {field: 'name', sort: true, align: "center", title: '租户名称'},
            {field: 'code', sort: true, align: "center", title: '租户的编码'},
            {field: 'dbName', sort: true, align: "center", title: '关联的数据库名称'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'createUser', sort: true, align: "center", title: '创建人'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {field: 'updateUser', sort: true, align: "center", title: '更新人'},
            {toolbar: '#tableBar', align: "center", title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    TenantInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(TenantInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    TenantInfo.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/tenantInfo/add';
    };

    /**
     * 导出excel按钮
     */
    TenantInfo.exportExcel = function () {
        var checkRows = table.checkStatus(TenantInfo.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    TenantInfo.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/tenantInfo/edit?tenantId=' + data.tenantId;
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    TenantInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/tenantInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(TenantInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("tenantId", data.tenantId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + TenantInfo.tableId,
        url: Feng.ctxPath + '/tenantInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: TenantInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        TenantInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        TenantInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        TenantInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + TenantInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            TenantInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            TenantInfo.onDeleteItem(data);
        }
    });
});
