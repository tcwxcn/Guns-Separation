layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 合作伙伴表管理
     */
    var ZPartner = {
        tableId: "zPartnerTable"
    };

    /**
     * 初始化表格的列
     */
    ZPartner.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'partnerId', hide: true, title: '主键id'},
            {field: 'picAddress', sort: true, title: '图片地址'},
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
    ZPartner.search = function () {
        var queryData = {};

        queryData['partnerId'] = $('#partnerId').val();
        queryData['picAddress'] = $('#picAddress').val();

        table.reload(ZPartner.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZPartner.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zPartner/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZPartner.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zPartner/edit?partnerId=' + data.partnerId
    };

    /**
     * 导出excel按钮
     */
    ZPartner.exportExcel = function () {
        var checkRows = table.checkStatus(ZPartner.tableId);
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
    ZPartner.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zPartner/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZPartner.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("partnerId", data.partnerId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZPartner.tableId,
        url: Feng.ctxPath + '/zPartner/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZPartner.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZPartner.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZPartner.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZPartner.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZPartner.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZPartner.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZPartner.onDeleteItem(data);
        }
    });
});
