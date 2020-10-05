layui.use(['table', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var laydate = layui.laydate;
    var $ax = layui.ax;

    /**
     * 管理
     */
    var Procinst = {
        tableId: "procinstTable"
    };

    /**
     * 初始化表格的列
     */
    Procinst.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id_', hide: true, title: ''},
            {field: 'proc_inst_id_', hide: true, sort: true, title: ''},
            {field: 'dgrm_resource_name_', hide: true, sort: true, title: ''},
            {field: 'deployment_id_', hide: true, sort: true, title: ''},
            {field: 'pname_', sort: true, title: '流程名称'},
            {field: 'initator', sort: true, title: '申请人'},
            {field: 'version_', sort: true, title: '流程版本'},
            {field: 'start_time_', sort: true, title: '开始时间'},
            {field: 'end_time_', sort: true, title: '结束时间'},
            {field: 'ztime', sort: true, title: '用时'},
            {
                field: 'delete_reason_', sort: true, title: '状态', templet: function (d) {
                    if (d.delete_reason_ === null) {
                        return "<span style='color: #87B87F;'>正常完成</span>";
                    } else {
                        return "<span style='color: red;'>作废</span>";
                    }
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作'}
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

    /**
     * 点击查询按钮
     */
    Procinst.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        queryData['lastStart'] = $("#lastStart").val();
        queryData['lastEnd'] = $("#lastEnd").val();
        table.reload(Procinst.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 点击流程信息
     *
     * @param data 点击按钮时候的行数据
     */
    Procinst.openDetailDlg = function (data) {
        layer.open({
            type: 2,
            title: '流程信息',
            area: ['900px', '500px'],
            content: Feng.ctxPath + '/taskHistory/processInfo?ID_=' + data.id_ + "&DGRM_RESOURCE_NAME_=" + data.dgrm_resource_name_ + "&PROC_INST_ID_=" + data.proc_inst_id_,
            end: function () {
                Procinst.search();
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Procinst.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/historyProc/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Procinst.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("procinstId", data.proc_inst_id_);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Procinst.tableId,
        url: Feng.ctxPath + '/historyProc/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Procinst.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Procinst.search();
    });

    // 工具条点击事件
    table.on('tool(' + Procinst.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'detail') {
            Procinst.openDetailDlg(data);
        } else if (layEvent === 'delete') {
            Procinst.onDeleteItem(data);
        }
    });
});
