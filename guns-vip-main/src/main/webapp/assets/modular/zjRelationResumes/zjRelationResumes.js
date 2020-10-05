layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 个人简历信息和用户信息关联表管理
     */
    var ZjRelationResumes = {
        tableId: "zjRelationResumesTable"
    };

    /**
     * 初始化表格的列
     */
    ZjRelationResumes.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'relationId', hide: true, title: '主键'},
            {field: 'resumesId', sort: true, title: '个人简历信息id'},
            {field: 'userInfoId', sort: true, title: '用户信息id'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ZjRelationResumes.search = function () {
        var queryData = {};

        queryData['resumesId'] = $('#resumesId').val();
        queryData['userInfoId'] = $('#userInfoId').val();

        table.reload(ZjRelationResumes.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZjRelationResumes.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zjRelationResumes/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZjRelationResumes.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zjRelationResumes/edit?relationId=' + data.relationId
    };

    /**
     * 导出excel按钮
     */
    ZjRelationResumes.exportExcel = function () {
        var checkRows = table.checkStatus(ZjRelationResumes.tableId);
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
    ZjRelationResumes.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zjRelationResumes/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZjRelationResumes.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("relationId", data.relationId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZjRelationResumes.tableId,
        url: Feng.ctxPath + '/zjRelationResumes/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZjRelationResumes.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZjRelationResumes.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZjRelationResumes.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZjRelationResumes.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZjRelationResumes.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZjRelationResumes.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZjRelationResumes.onDeleteItem(data);
        }
    });
});
