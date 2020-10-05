layui.use(['table', 'layer', 'form', 'formSelects', 'layedit', 'ax', 'element'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var $ax = layui.ax;
    var form = layui.form;
    var formSelects = layui.formSelects;
    var layedit = layui.layedit;
    var element = layui.element;
    var table = layui.table;
    var editIndex = layedit.build('opinion'); // 建立编辑器

    /**
     * 所需参数
     */
    var id = $("#ID_").val();
    var fileName = $("#DGRM_RESOURCE_NAME_").val();
    var procInstId = $("#PROC_INST_ID_").val();

    /**
     * 流程日志表格
     */
    var HitaskViewTable = {
        tableId: "hitaskViewTable"
    };

    /**
     * 初始化表格的列
     */
    HitaskViewTable.initColumn = function () {
        return [[
            {type: 'numbers', title: "步骤"},
            {field: 'act_name_', title: '任务节点'},
            {field: 'assignee_', title: '办理人'},
            {field: 'start_time_', title: '审批开始时间'},
            {field: 'end_time_', title: '审批结束时间'},
            {field: 'ztime', title: '用时'},
            {field: 'text_', title: '审批意见'}
        ]];
    };

    /**
     * 初始化数据
     */
    $(function () {
        var ajax = new $ax(Feng.ctxPath + "/taskHistory/view", function (data) {

            console.log(data);

            //初始化请假单信息
            var info = data.data.varList;
            $("#leaveUser").val(info["请假人员"]);
            $("#leaveStarttime").val(info["开始时间"]);
            $("#leaveEndtime").val(info["结束时间"]);
            $("#leaveDuration").val(info["请假时长"]);
            $("#leaveType").val(info["请假类型"]);
            $("#leaveReason").val(info["请假事由"]);
            $("#leaveLoginUser").val(info["username"]);

            //渲染流程日志表格
            table.render({
                elem: '#' + HitaskViewTable.tableId,
                page: false,
                height: "full-158",
                cellMinWidth: 100,
                cols: HitaskViewTable.initColumn(),
                data: data.data.hitaskList
            });

            //加载流程图
            $("#actImg").attr("src", data.data.imgSrc);

        }, function (data) {
            Feng.error("获取失败！" + data.responseJSON.message)
        });

        ajax.set("id", id);
        ajax.set("fileName", fileName);
        ajax.set("procInstId", procInstId);

        ajax.start();
    });

    /**
     * 取消
     */
    $("#cancal").click(function () {
        parent.layer.close(handleDlg.index);
    });
});