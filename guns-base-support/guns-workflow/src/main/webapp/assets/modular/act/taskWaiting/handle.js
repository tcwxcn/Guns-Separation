var Handle = {
    nextObject: '',
    assignee: ""
};

layui.use(['table', 'layer', 'layedit', 'ax', 'element','admin'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var $ax = layui.ax;
    var layedit = layui.layedit;
    var element = layui.element;
    var table = layui.table;
    var admin = layui.admin;
    var editIndex = layedit.build('opinion'); // 建立编辑器

    var handleDlg = {
        index: parent.layer.getFrameIndex(window.name)
    };

    /**
     * 所需参数
     */
    var id = $("#ID_").val();
    var fileName = $("#DGRM_RESOURCE_NAME_").val();
    var procInstId = $("#PROC_INST_ID_").val();

    /**
     * 流程日志表格
     */
    var HitaskListTable = {
        tableId: "hitaskListTable"
    };

    /**
     * 初始化表格的列
     */
    HitaskListTable.initColumn = function () {
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
        var ajax = new $ax(Feng.ctxPath + "/taskWaiting/getHandleData", function (data) {

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
                elem: '#' + HitaskListTable.tableId,
                page: false,
                height: "full-158",
                cellMinWidth: 100,
                cols: HitaskListTable.initColumn(),
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
     * 指定人员
     */
    $("#setUser").click(function () {
        layer.open({
            type: 2,
            title: "选择办理人",
            closeBtn: 1,
            shade: [0],
            area: ['500px', '400px'],
            offset: 'auto',
            anim: 5,
            content: Feng.ctxPath + "/taskWaiting/selectDealer",
            end: function () {
                $("#username").val(Handle.nextObject);
            }
        });

    });

    /**
     * 指定角色
     */
    $("#setRole").click(function () {
        layer.open({
            type: 2,
            title: "选择角色",
            closeBtn: 1,
            shade: [0],
            area: ['500px', '400px'],
            offset: 'auto',
            anim: 5,
            content: Feng.ctxPath + "/taskWaiting/selectRole",
            end: function () {
                $("#username").val(Handle.nextObject);
            }
        });
    });

    /**
     * 批准
     */
    $("#handleYes").click(function () {

        var opinion = layedit.getContent(editIndex);

        var ASSIGNEE_ = Handle.assignee;
        if (ASSIGNEE_ == "" || ASSIGNEE_ == null) {
            ASSIGNEE_ = 'admin';
        }
        $.ajax({
            url: Feng.ctxPath + "/taskWaiting/handle",
            data: {
                ASSIGNEE_: ASSIGNEE_,
                ID_: id,
                msg: "yes",
                OPINION: opinion,
                PROC_INST_ID_: procInstId
            },
            success: function (data) {
                admin.putTempData('formOk', true);
                parent.layer.close(handleDlg.index);
            },
            error: function (data) {
                Feng.error("获取失败！" + data.responseJSON.message);
            }
        });
    });

    /**
     * 驳回
     */
    $("#handleNo").click(function () {

        var opinion = layedit.getContent(editIndex);
        var ASSIGNEE_ = Handle.assignee;
        if (ASSIGNEE_ == "" || ASSIGNEE_ == null) {
            ASSIGNEE_ = 'admin';
        }
        $.ajax({
            url: Feng.ctxPath + "/taskWaiting/handle",
            data: {
                ASSIGNEE_: ASSIGNEE_,
                ID_: id,
                msg: "no",
                OPINION: opinion,
                PROC_INST_ID_: procInstId
            },
            success: function (data) {
                admin.putTempData('formOk', true);
                parent.layer.close(handleDlg.index);
            },
            error: function (data) {
                Feng.error("获取失败！" + data.responseJSON.message);
            }
        });
    });

    /**
     * 作废
     */
    $("#delete").click(function () {
        parent.layer.prompt({
            title: '请输入作废理由：',
            formType: 2,
            skin: 'layui-layer-admin layui-layer-prompt',
            shade: .1
        }, function (value, index, elem) {
            var ajax = new $ax(Feng.ctxPath + "/process/deleteAct", function (data) {
                Feng.success("操作成功");
                admin.putTempData('formOk', true);
                parent.layer.close(handleDlg.index);
            }, function (data) {
                Feng.error("获取失败！" + data.responseJSON.message);
            });
            ajax.set("procInstId", procInstId);
            ajax.set("reason", value);
            ajax.start();
        });
    });

    /**
     * 取消
     */
    $("#cancal").click(function () {
        admin.putTempData('formOk', true);
        parent.layer.close(handleDlg.index);
    });
});