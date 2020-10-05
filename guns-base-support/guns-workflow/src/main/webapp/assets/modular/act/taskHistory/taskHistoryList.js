layui.use(['jquery', 'table', 'layer', 'laydate'], function () {

    var $ = layui.$;
    var table = layui.table;
    var laydate = layui.laydate;
    var layer = layui.layer;

    var Hitask = {
        tableId: "hitaskTable"
    };

    // 基础数据
    Hitask.initColumn = function () {
        return [[
            {type: 'numbers'},
            {field: 'id_', hide: true, title: ''},
            {field: 'dgrm_resource_name_', hide: true, title: ''},
            {field: 'proc_inst_id_', hide: true, title: ''},
            {field: 'pname_', sort: true, title: '流程名称'},
            {field: 'initator', sort: true, title: '申请人'},
            {field: 'assignee_', sort: true, title: '办理人or角色'},
            {field: 'name_', sort: true, title: '任务节点'},
            {field: 'start_time_', sort: true, title: '开始时间'},
            {field: 'end_time_', sort: true, title: '办完时间'},
            {field: 'ztime', sort: true, title: '用时'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    // 定义查询事件
    Hitask.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        queryData['lastStart'] = $("#lastStart").val();
        queryData['lastEnd'] = $("#lastEnd").val();
        table.reload(Hitask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    // 表格初始化
    table.render({
        elem: '#' + Hitask.tableId,
        url: Feng.ctxPath + '/taskHistory/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Hitask.initColumn()
    });

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

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Hitask.search();
    });

    // 流程信息
    Hitask.openProcessInfoDlg = function (data) {
        layer.open({
            type: 2,
            title: '流程信息',
            area: ['900px', '500px'],
            content: Feng.ctxPath + '/taskHistory/processInfo?ID_=' + data.id_ + "&DGRM_RESOURCE_NAME_=" + data.dgrm_resource_name_ + "&PROC_INST_ID_=" + data.proc_inst_id_,
            end: function () {
                Hitask.search();
            }
        });
    };

    // 工具条点击事件
    table.on('tool(' + Hitask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'processInfo') {
            Hitask.openProcessInfoDlg(data);
        }
    });
});