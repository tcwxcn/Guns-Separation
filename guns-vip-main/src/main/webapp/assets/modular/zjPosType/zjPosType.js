layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 职位类型表管理
     */
    var ZjPosType = {
        tableId: "zjPosTypeTable"
    };

    /**
     * 初始化表格的列
     */
    ZjPosType.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'posTypeId', hide: true, title: '主键id'},
            {field: 'name', sort: true, title: '职位类型名称'},
            {field: 'sort', sort: true, title: '顺序'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'createUser', sort: true, title: '创建者'},
            {field: 'updateUser', sort: true, title: '更新者'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ZjPosType.search = function () {
        var queryData = {};

        queryData['name'] = $('#name').val();

        table.reload(ZjPosType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZjPosType.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zjPosType/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZjPosType.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zjPosType/edit?posTypeId=' + data.posTypeId
    };

    /**
     * 导出excel按钮
     */
    ZjPosType.exportExcel = function () {
        var checkRows = table.checkStatus(ZjPosType.tableId);
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
    ZjPosType.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zjPosType/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZjPosType.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("posTypeId", data.posTypeId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZjPosType.tableId,
        url: Feng.ctxPath + '/zjPosType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZjPosType.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZjPosType.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZjPosType.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZjPosType.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZjPosType.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZjPosType.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZjPosType.onDeleteItem(data);
        }
    });
});
