layui.use(['table', 'admin', 'ax', 'func', 'layer', 'laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var laydate = layui.laydate;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var layer = layui.layer;

    /**
     * 管理
     */
    var Task = {
        tableId: "taskTable"
    };

    /**
     * 初始化表格的列
     */
    Task.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id_', hide: true, title: ''},
            {field: 'dgrm_resource_name_', hide: true, title: ''},
            {field: 'task_def_key_', hide: true, title: ''},
            {field: 'task_def_key_', hide: true, title: ''},
            {field: 'priority_', hide: true, title: ''},
            {field: 'tenant_id_', hide: true, title: ''},
            {field: 'suspension_state_', hide: true, title: ''},
            {field: 'rev_', hide: true, title: ''},
            {field: 'execution_id_', hide: true, title: ''},
            {field: 'proc_inst_id_', hide: true, title: ''},

            {field: 'pname_', sort: true, title: '流程名称'},
            {field: 'initator', sort: true, title: '申请人'},
            {field: 'assignee_', sort: true, title: '当前节点(待办人)'},
            {field: 'name_', sort: true, title: '当前任务'},
            {field: 'create_time_', sort: true, title: '创建时间'},
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

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Task.tableId,
        url: Feng.ctxPath + '/taskWaiting/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Task.initColumn()
    });

    /**
     * 点击查询按钮
     */
    Task.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        queryData['lastStart'] = $("#lastStart").val();
        queryData['lastEnd'] = $("#lastEnd").val();
        table.reload(Task.tableId, {
            where: queryData, page: {curr: 1}
        });
    };


    /**
     * 点击委派
     *
     * @param data 点击按钮时候的行数据
     */
    Task.openDelegateDlg = function (data) {
        layer.open({
            type: 2,
            title: '委派',
            area: ['800px', '400px'],
            content: Feng.ctxPath + '/taskWaiting/delegatePage?ID_=' + data.id_,
            end: function () {
                Task.search();
            }
        });
    };

    /**
     * 点击办理
     *
     * @param data 点击按钮时候的行数据
     */
    Task.onHandleItem = function (data) {
        func.open({
            title: '办理',
            content: Feng.ctxPath + '/taskWaiting/handlePage?ID_=' + data.id_ + "&DGRM_RESOURCE_NAME_=" + data.dgrm_resource_name_ + "&PROC_INST_ID_=" + data.proc_inst_id_,
            tableId: Task.tableId
        });
    };

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Task.search();
    });

    // 工具条点击事件
    table.on('tool(' + Task.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delegate') {
            Task.openDelegateDlg(data);
        } else if (layEvent === 'handle') {
            Task.onHandleItem(data);
        }
    });
});
