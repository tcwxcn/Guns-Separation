layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 职位信息表管理
     */
    var ZPosInfo = {
        tableId: "zPosInfoTable"
    };

    /**
     * 初始化表格的列
     */
    ZPosInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'posInfoId', hide: true, title: '主键id'},
            {field: 'title', sort: true, title: '标题'},
            {field: 'posName', sort: true, title: '职位名称'},
            {field: 'content', sort: true, title: '工作内容'},
            {field: 'request', sort: true, title: '工作要求'},
            {field: 'date', sort: true, title: '工作日期'},
            {field: 'time', sort: true, title: '工作时间'},
            {field: 'location', sort: true, title: '工作地点'},
            {field: 'demandNum', sort: true, title: '需求人数'},
            {field: 'salary', sort: true, title: '薪资待遇'},
            {field: 'settlementInterval', sort: true, title: '结算周期'},
            {field: 'welfare', sort: true, title: '福利'},
            {field: 'contact', sort: true, title: '联系方式'},
            {field: 'notice', sort: true, title: '注意事项'},
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
    ZPosInfo.search = function () {
        var queryData = {};

        queryData['posName'] = $('#posName').val();
        queryData['location'] = $('#location').val();
        queryData['welfare'] = $('#welfare').val();

        table.reload(ZPosInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZPosInfo.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zPosInfo/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZPosInfo.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zPosInfo/edit?posInfoId=' + data.posInfoId
    };

    /**
     * 导出excel按钮
     */
    ZPosInfo.exportExcel = function () {
        var checkRows = table.checkStatus(ZPosInfo.tableId);
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
    ZPosInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zPosInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZPosInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("posInfoId", data.posInfoId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZPosInfo.tableId,
        url: Feng.ctxPath + '/zPosInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZPosInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZPosInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZPosInfo.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZPosInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZPosInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZPosInfo.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZPosInfo.onDeleteItem(data);
        }
    });
});
