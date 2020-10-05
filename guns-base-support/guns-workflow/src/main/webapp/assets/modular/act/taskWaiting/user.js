layui.use(['table', 'laydate'], function () {
    var table = layui.table;
    var laydate = layui.laydate;
    var $ = layui.$;

    /**
     * 系统管理--用户管理
     */
    var MgrUser = {
        tableId: "userTable",    //表格id
        condition: {
            name: "",
            deptId: "",
            timeLimit: ""
        }
    };

    /**
     * 初始化表格的列
     */
    MgrUser.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'userId', hide: true, sort: true, title: '用户id'},
            {field: 'account', align: "center", sort: true, title: '账号'},
            {field: 'name', align: "center", sort: true, title: '姓名'},
            {field: 'deptName', align: "center", sort: true, title: '部门'},
            {field: 'positionName', hide: true, align: "center", sort: true, title: '职位'},
            {field: 'phone', hide: true, align: "center", sort: true, title: '电话', minWidth: 117},
            {field: 'createTime', hide: true, align: "center", sort: true, title: '创建时间', minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 100}
        ]];
    };

    /**
     * 点击查询按钮
     */
    MgrUser.search = function () {
        var queryData = {};
        queryData['deptId'] = "";
        queryData['name'] = $("#name").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        table.reload(MgrUser.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 指定人员
     *
     * @param data 点击按钮时候的行数据
     */
    MgrUser.onSetDelegateUser = function (data) {
        var account = data.account;
        var username = data.name;

        $("#delegateObj").val(username);
        $("#setValue").attr("value",account);
        $("#setFlag").attr("value","true");
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + MgrUser.tableId,
        url: Feng.ctxPath + '/mgr/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: MgrUser.initColumn()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MgrUser.search();
    });

    // 工具条点击事件
    table.on('tool(' + MgrUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
        } else if (layEvent === 'setDelegateUser') {
            MgrUser.onSetDelegateUser(data);
        }
    });
});
