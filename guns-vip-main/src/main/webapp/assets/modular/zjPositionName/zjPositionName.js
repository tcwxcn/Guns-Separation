layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 工作职位名称表管理
     */
    var ZjPositionName = {
        tableId: "zjPositionNameTable"
    };

    /**
     * 初始化表格的列
     */
    ZjPositionName.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'positionId', hide: true, title: '主键'},
            {field: 'positionName', sort: true, title: '职位名称'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'updateUser', sort: true, title: '更新人'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ZjPositionName.search = function () {
        var queryData = {};

        queryData['positionName'] = $('#positionName').val();

        table.reload(ZjPositionName.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZjPositionName.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zjPositionName/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZjPositionName.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zjPositionName/edit?positionId=' + data.positionId
    };

    /**
     * 导出excel按钮
     */
    ZjPositionName.exportExcel = function () {
        var checkRows = table.checkStatus(ZjPositionName.tableId);
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
    ZjPositionName.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zjPositionName/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZjPositionName.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("positionId", data.positionId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZjPositionName.tableId,
        url: Feng.ctxPath + '/zjPositionName/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZjPositionName.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZjPositionName.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZjPositionName.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZjPositionName.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZjPositionName.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZjPositionName.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZjPositionName.onDeleteItem(data);
        }
    });
});
