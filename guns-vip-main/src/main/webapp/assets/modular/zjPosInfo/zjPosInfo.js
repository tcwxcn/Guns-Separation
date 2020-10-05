layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 职业信息表管理
     */
    var ZjPosInfo = {
        tableId: "zjPosInfoTable"
    };

    /**
     * 初始化表格的列
     */
    ZjPosInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'posInfoId', hide: true, title: '主键id'},
            {field: 'title', sort: true, title: '标题'},
            {field: 'job', sort: true, title: '工作职业'},
            {field: 'num', sort: true, title: '需求人数'},
            {field: 'time', sort: true, title: '工作时间'},
            {field: 'location', sort: true, title: '工作地点'},
            {field: 'content', sort: true, title: '工作内容'},
            {field: 'request', sort: true, title: '职位要求'},
            {field: 'salary', sort: true, title: '薪资待遇'},
            {field: 'settlementInterval', sort: true, title: '结算周期'},
            {field: 'contact', sort: true, title: '联系方式'},
            {field: 'sort', sort: true, title: '顺序'},
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
    ZjPosInfo.search = function () {
        var queryData = {};

        queryData['title'] = $('#title').val();
        queryData['job'] = $('#job').val();
        queryData['location'] = $('#location').val();

        table.reload(ZjPosInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZjPosInfo.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zjPosInfo/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZjPosInfo.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zjPosInfo/edit?posInfoId=' + data.posInfoId
    };

    /**
     * 导出excel按钮
     */
    ZjPosInfo.exportExcel = function () {
        var checkRows = table.checkStatus(ZjPosInfo.tableId);
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
    ZjPosInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zjPosInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZjPosInfo.tableId);
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
        elem: '#' + ZjPosInfo.tableId,
        url: Feng.ctxPath + '/zjPosInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZjPosInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZjPosInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZjPosInfo.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZjPosInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZjPosInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZjPosInfo.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZjPosInfo.onDeleteItem(data);
        }
    });
});
