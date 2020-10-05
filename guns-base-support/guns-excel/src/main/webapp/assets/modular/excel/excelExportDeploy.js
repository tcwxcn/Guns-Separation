layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * excel导出配置管理
     */
    var ExcelExportDeploy = {
        tableId: "excelExportDeployTable"
    };

    /**
     * 初始化表格的列
     */
    ExcelExportDeploy.initColumn = function () {
        return [[
            {field: 'id', hide: true, title: ''},
            {field: 'name', sort: true, title: 'excel导出配置名称'},
            {field: 'title', sort: true, title: '文件名称'},
            {field: 'nid', sort: true, title: '唯一标识'},
            {templet: '#template', sort: true, title: '模版文件'},
            {templet: '#showUrl', sort: true, title: '数据查询'},
            {templet: '#exportUrl', sort: true, title: '导出链接'},
            {field: 'statusName', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ExcelExportDeploy.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(ExcelExportDeploy.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    ExcelExportDeploy.openAddDlg = function () {
        func.open({
            title: '添加excel导出配置',
            content: Feng.ctxPath + '/excelExportDeploy/add',
            tableId: ExcelExportDeploy.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    ExcelExportDeploy.openEditDlg = function (data) {
        func.open({
            title: '修改excel导出配置',
            content: Feng.ctxPath + '/excelExportDeploy/edit?id=' + data.id,
            tableId: ExcelExportDeploy.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    ExcelExportDeploy.exportExcel = function () {
        var checkRows = table.checkStatus(ExcelExportDeploy.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    ExcelExportDeploy.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/excelExportDeploy/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ExcelExportDeploy.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ExcelExportDeploy.tableId,
        url: Feng.ctxPath + '/excelExportDeploy/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ExcelExportDeploy.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ExcelExportDeploy.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        ExcelExportDeploy.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        ExcelExportDeploy.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ExcelExportDeploy.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ExcelExportDeploy.openEditDlg(data);
        } else if (layEvent === 'delete') {
            ExcelExportDeploy.onDeleteItem(data);
        }
    });
});
