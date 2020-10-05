layui.use(['table','ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var func = layui.func;

    /**
     * 管理
     */
    var Procdef = {
        tableId: "procdefTable"
    };

    /**
     * 初始化表格的列
     */
    Procdef.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: "numbers", align: "center", title: '序号'},
            {field: 'id', hide: true},
            {field: 'deploymentId', hide: true},
            {field: 'name', sort: true, align: "center", title: '名称'},
            {field: 'key', sort: true, align: "center", title: '流程定义KEY'},
            {
                field: 'version', sort: true, align: "center", title: '版本', templet: function (d) {
                    return 'v.' + d.version;
                }
            },
            {field: 'deployTime', sort: true, align: "center", title: '部署时间'},
            {field: 'resourceName', sort: true, align: "center", title: '流程bpmn文件名称'},
            {field: 'dgrmResourceName', sort: true, align: "center", title: '流程图片名称'},
            {
                field: 'suspensionState', sort: true, align: "center", title: '状态', templet: function (d) {
                    if (d.suspensionState === 1) {
                        return "<span style='color: #5FB878;'>已激活</span>";
                    } else {
                        return "<span style='color: #fe7300;'>已挂起</span>";
                    }
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 380}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Procdef.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        table.reload(Procdef.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 导入流程
     */
    Procdef.openAddDlg = function () {
        func.open({
            title: '导入流程',
            content: Feng.ctxPath + '/process/add',
            tableId: Procdef.tableId,
            height: 250
        });
    };

    /**
     * 映射模型
     */
    Procdef.map = function (data) {
        var ajax = new $ax(Feng.ctxPath + "/model/saveModelFromPro", function (data) {
            Feng.success("映射成功！");
        }, function (data) {
            Feng.error("映射失败！" + data.responseJSON.message)
        });
        ajax.set("processDefinitionId", data.id);
        ajax.start();
    };

    /**
     * 下载模型
     */
    Procdef.download = function (data) {
        window.location.href = Feng.ctxPath + '/process/download?deploymentId=' + data.deploymentId;
    };

    /**
     * 挂起或者激活
     */
    Procdef.hangActive = function (data, status) {
        var ajax = new $ax(Feng.ctxPath + "/process/onoffPro", function (data) {
            if (status === 1) {
                Feng.success("激活成功！");
            } else {
                Feng.success("挂起成功！");
            }
            table.reload(Procdef.tableId);
        }, function (data) {
            if (status === 1) {
                Feng.error("激活失败！" + data.responseJSON.message)
            } else {
                Feng.error("挂起失败！" + data.responseJSON.message)
            }
        });
        ajax.set("id", data.id);
        ajax.set("status", status);
        ajax.start();
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Procdef.delete = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/process/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Procdef.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("deploymentId", data.deploymentId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    table.render({
        elem: '#' + Procdef.tableId,
        url: Feng.ctxPath + '/process/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Procdef.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Procdef.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Procdef.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Procdef.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'map') {
            Procdef.map(data);
        } else if (layEvent === 'download') {
            Procdef.download(data);
        } else if (layEvent === 'hangup') {
            Procdef.hangActive(data, 2);
        } else if (layEvent === 'active') {
            Procdef.hangActive(data, 1);
        } else if (layEvent === 'delete') {
            Procdef.delete(data);
        }
    });
});
