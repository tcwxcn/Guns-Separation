layui.use(['table', 'admin', 'ax', 'func', 'layer', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var layer = layui.layer;
    var form = layui.form;

    /**
     * 管理
     */
    var Model = {
        tableId: "modelTable"
    };

    /**
     * 初始化表格的列
     */
    Model.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {type: "numbers", align: "center", title: '序号'},
            {field: 'name', align: "center", title: '模型名称'},
            {field: 'cateName', align: "center", title: '分类'},
            {field: 'createTime', align: "center", title: '创建时间'},
            {field: 'lastUpdateTime', align: "center", title: '更新时间'},
            {
                field: 'version', align: "center", title: '版本', templet: function (d) {
                    return 'v.' + d.version;
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 380}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Model.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        queryData['category'] = $("#category").val();
        table.reload(Model.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Model.openAddDlg = function () {
        func.open({
            title: '添加',
            content: Feng.ctxPath + '/model/add',
            tableId: Model.tableId
        });
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    Model.openEditDlg = function (data) {
        func.open({
            title: '修改',
            content: Feng.ctxPath + '/model/edit?id=' + data.id,
            tableId: Model.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Model.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/model/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Model.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("modelId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /**
     * 设计流程图
     */
    Model.onDesignItem = function (data) {
        func.open({
            title: '流程设计器',
            content: Feng.ctxPath + '/activiti-editor/editor.html?modelId=' + data.id,
            resize: true,
            maxmin: true,
            width: '1200px'
        });
    };

    /**
     * 发布部署流程
     */
    Model.onDeployItem = function (data) {
        var ajax = new $ax(Feng.ctxPath + "/model/deployment", function (data) {
            Feng.success("部署成功！");
        }, function (data) {
            Feng.error("部署失败！" + data.responseJSON.message)
        });
        ajax.set("modelId", data.id);
        ajax.start();
    };

    /**
     * 预览流程图
     */
    Model.onPreviewItem = function (data) {
        var ajax = new $ax(Feng.ctxPath + "/model/isCanExportXml", function (result) {
            func.open({
                title: '预览流程xml',
                content: Feng.ctxPath + '/model/modelView?modelId=' + data.id,
                resize: true,
                maxmin: true,
                width: '1200px'
            });
        }, function (data) {
            Feng.error("预览失败！" + data.responseJSON.message);
        });
        ajax.set("modelId", data.id);
        ajax.start();
    };

    /**
     * 导出item
     */
    Model.onExportItem = function (data) {
        var ajax = new $ax(Feng.ctxPath + "/model/isCanExportXml", function (result) {
            window.location.href = Feng.ctxPath + '/model/exportXml?modelId=' + data.id;
        }, function (data) {
            Feng.error("导出失败！" + data.responseJSON.message);
        });
        ajax.set("modelId", data.id);
        ajax.start();
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Model.tableId,
        url: Feng.ctxPath + '/model/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Model.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Model.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Model.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Model.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Model.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Model.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Model.onDeleteItem(data);
        } else if (layEvent === 'design') {
            Model.onDesignItem(data);
        } else if (layEvent === 'deploy') {
            Model.onDeployItem(data);
        } else if (layEvent === 'preview') {
            Model.onPreviewItem(data);
        } else if (layEvent === 'export') {
            Model.onExportItem(data);
        }
    });

    //初始化搜索框
    var ajax = new $ax(Feng.ctxPath + "/dict/listDictsByCode", function (data) {
        for (var i = 0; i < data.data.length; i++) {
            var name = data.data[i].name;
            var code = data.data[i].code;
            $("#category").append('<option value="' + code + '">' + name + '</option>');
        }
        form.render();
    }, function (data) {
    });
    ajax.set("dictTypeCode", "FLOW_CATEGARY");
    ajax.start();
});
