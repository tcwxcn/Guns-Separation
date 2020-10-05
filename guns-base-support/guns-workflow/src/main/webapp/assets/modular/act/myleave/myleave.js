layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 管理
     */
    var Myleave = {
        tableId: "myLeaveTable"
    };

    /**
     * 初始化表格的列
     */
    Myleave.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'myleaveId', hide: true, title: ''},
            {field: 'username', sort: true, title: '用户名'},
            {field: 'type', sort: true, title: '类型'},
            {field: 'starttime', sort: true, title: '开始时间'},
            {field: 'endtime', sort: true, title: '结束时间'},
            {field: 'whenlong', sort: true, title: '时长'},
            {field: 'reason', sort: true, title: '事由'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 加载请假类型
     */
    func.initDictSelect("/myleave/getLeaves", "type", "dictName", "dictName");

    /**
     * 点击查询按钮
     */
    Myleave.search = function () {
        var queryData = {};
        queryData['keywords'] = $("#keywords").val();
        queryData['type'] = $("#type").val();
        table.reload(Myleave.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Myleave.openAddDlg = function () {
        func.open({
            title: '添加',
            height: 600,
            content: Feng.ctxPath + '/myleave/add',
            tableId: Myleave.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Myleave.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/myleave/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Myleave.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("myleaveId", data.myleaveId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    table.render({
        elem: '#' + Myleave.tableId,
        url: Feng.ctxPath + '/myleave/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Myleave.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Myleave.search();
    });

    // 批量删除点击事件
    $('#btnBatchDelete').click(function () {
        var operation = function () {
            var idsArray = [];
            var checkStatus = table.checkStatus(Myleave.tableId);

            if (checkStatus.data == null || checkStatus.data.length < 1) {
                Feng.error("无选中项");
            } else {
                for (var i = 0; i < checkStatus.data.length; i++) {
                    idsArray.push(checkStatus.data[i].myleaveId);
                }
                $.ajax({
                    url: Feng.ctxPath + "/myleave/batchDelete",
                    data: {
                        ids: idsArray
                    },
                    traditional: true,
                    success: function (data) {
                        Feng.success("删除成功!");
                        table.reload(Myleave.tableId);
                    },
                    error: function (data) {
                        Feng.error("获取失败！" + data.responseJSON.message);
                    }
                });
            }
        };
        Feng.confirm("确认要删除选中项?", operation);
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Myleave.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Myleave.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'delete') {
            Myleave.onDeleteItem(data);
        }
    });
});
