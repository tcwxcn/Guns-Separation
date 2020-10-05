layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 职位表管理
     */
    var SysPosition = {
        tableId: "sysPositionTable"
    };

    /**
     * 初始化表格的列
     */
    SysPosition.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'positionId', hide: true, title: '主键id'},
            {field: 'name', sort: true, title: '职位名称'},
            {field: 'code', sort: true, title: '职位编码'},
            {field: 'sort', sort: true, title: '顺序'},
            {field: 'status', sort: true, title: '状态(字典)'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateUser', sort: true, title: '更新者'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'createUser', sort: true, title: '创建者'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    SysPosition.search = function () {
        var queryData = {};

        queryData['name'] = $('#name').val();

        table.reload(SysPosition.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    SysPosition.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/sysPosition/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    SysPosition.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/sysPosition/edit?positionId=' + data.positionId
    };

    /**
     * 导出excel按钮
     */
    SysPosition.exportExcel = function () {
        var checkRows = table.checkStatus(SysPosition.tableId);
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
    SysPosition.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sysPosition/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SysPosition.tableId);
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
        elem: '#' + SysPosition.tableId,
        url: Feng.ctxPath + '/sysPosition/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SysPosition.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SysPosition.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    SysPosition.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        SysPosition.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + SysPosition.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SysPosition.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            SysPosition.onDeleteItem(data);
        }
    });
});
