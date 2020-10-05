layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 职位名称表管理
     */
    var ZPosName = {
        tableId: "zPosNameTable"
    };

    /**
     * 初始化表格的列
     */
    ZPosName.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'posNameId', hide: true, title: '主键id'},
            {field: 'pid', sort: true, title: '父id'},
            {field: 'name', sort: true, title: '职位名称'},
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
    ZPosName.search = function () {
        var queryData = {};

        queryData['posNameId'] = $('#posNameId').val();
        queryData['pid'] = $('#pid').val();
        queryData['name'] = $('#name').val();

        table.reload(ZPosName.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZPosName.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zPosName/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZPosName.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zPosName/edit?posNameId=' + data.posNameId
    };

    /**
     * 导出excel按钮
     */
    ZPosName.exportExcel = function () {
        var checkRows = table.checkStatus(ZPosName.tableId);
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
    ZPosName.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zPosName/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZPosName.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("posNameId", data.posNameId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZPosName.tableId,
        url: Feng.ctxPath + '/zPosName/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZPosName.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZPosName.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZPosName.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZPosName.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZPosName.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZPosName.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZPosName.onDeleteItem(data);
        }
    });
});
