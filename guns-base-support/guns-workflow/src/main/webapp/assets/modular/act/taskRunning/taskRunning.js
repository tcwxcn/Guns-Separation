layui.use(['table', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var laydate = layui.laydate;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 运行中流程
     */
    var RuTask = {
        tableId: "ruTaskTable"
    };

    /**
     * 初始化表格的列
     */
    RuTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {type: "numbers", align: "center", title: '序号'},
            {field: 'id', hide: true, title: ''},
            {field: 'pname', sort: true, title: '流程名称'},
            {field: 'initator', sort: true, title: '申请人'},
            {field: 'assignee', sort: true, title: '当前节点(审批人)'},
            {field: 'name', sort: true, title: '当前任务'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {
                field: 'suspensionState', sort: true, title: '状态', templet: function (d) {
                    if (d.suspensionState === 1) {
                        return "<span style='color: #5FB878;'>正在运行</span>";
                    } else {
                        return "<span style='color: #fe7300;'>已挂起</span>";
                    }
                }
            },
            {field: 'rev', sort: true, hide: true, title: ''},
            {field: 'dgrmResourceName', sort: true, hide: true, title: ''},
            {field: 'executionId', sort: true, hide: true, title: ''},
            {field: 'priority', sort: true, hide: true, title: ''},
            {field: 'procDefId', sort: true, hide: true, title: ''},
            {field: 'procInstId', sort: true, hide: true, title: ''},
            {field: 'taskDefKey', sort: true, hide: true, title: ''},
            {field: 'tenantId', sort: true, hide: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 250}
        ]];
    };

    //渲染时间选择框
    laydate.render({
        elem: '#lastStart',
        range: false,
        max: Feng.currentDate()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#lastEnd',
        range: false,
        max: Feng.currentDate()
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + RuTask.tableId,
        url: Feng.ctxPath + '/taskRunning/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: RuTask.initColumn()
    });

    /**
     * 点击查询按钮
     */
    RuTask.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        queryData['lastStart'] = $("#lastStart").val();
        queryData['lastEnd'] = $("#lastEnd").val();
        table.reload(RuTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        RuTask.search();
    });

    /**
     * 点击流程信息
     *
     * @param data 点击按钮时候的行数据
     */
    RuTask.openInfoDlg = function (data) {
        layer.open({
            type: 2,
            title: '流程信息',
            area: ['900px', '500px'],
            content: Feng.ctxPath + '/taskHistory/processInfo?ID_=' + data.id + "&DGRM_RESOURCE_NAME_=" + data.dgrmResourceName + "&PROC_INST_ID_=" + data.procInstId,
            end: function () {
                RuTask.search();
            }
        });
    };

    /**
     * 委派
     *
     * @param data 点击按钮时候的行数据
     */
    RuTask.openDelegateDlg = function (data) {
        layer.open({
            type: 2,
            title: '指定委派对象',
            area: ['800px', '400px'],
            content: Feng.ctxPath + '/taskWaiting/delegatePage?ID_=' + data.id,
            end: function () {
                Task.search();
            }
        });
    };

    /**
     * 挂起 激活
     *
     * @param data 点击按钮时候的行数据
     */
    RuTask.openHangDlg = function (data, status) {
        var ajax = new $ax(Feng.ctxPath + "/process/onoffTask", function (data) {
            if (status === 1) {
                Feng.success("激活成功！");
                table.reload(RuTask.tableId);
            } else {
                Feng.success("挂起成功！");
                table.reload(RuTask.tableId);
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
     * 点击作废
     *
     * @param data 点击按钮时候的行数据
     */
    RuTask.onDeleteItem = function (data) {
        parent.layer.prompt({
            title: '请输入作废理由：',
            formType: 2,
            skin: 'layui-layer-admin layui-layer-prompt',
            shade: .1
        }, function (value, index, elem) {
            var ajax = new $ax(Feng.ctxPath + "/process/deleteAct", function (data) {
                Feng.success("操作成功");
                table.reload(RuTask.tableId);
            }, function (data) {
                Feng.error("操作失败！" + data.responseJSON.message);
            });
            ajax.set("procInstId",data.procInstId);
            ajax.set("reason",value);
            ajax.start();
            parent.layer.close(index);
        });
    };


    // 工具条点击事件
    table.on('tool(' + RuTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'info') {
            RuTask.openInfoDlg(data);
        } else if (layEvent === 'delegate') {
            RuTask.openDelegateDlg(data);
        } else if (layEvent === 'hang') {
            RuTask.openHangDlg(data, 2);
        } else if (layEvent === 'active') {
            RuTask.openHangDlg(data, 1);
        } else if (layEvent === 'delete') {
            RuTask.onDeleteItem(data);
        }
    });
});
