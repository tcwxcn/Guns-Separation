layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理员表管理
     */
    var SysUser = {
        tableId: "sysUserTable"
    };

    /**
     * 初始化表格的列
     */
    SysUser.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'userId', hide: true, title: '主键id'},
            // {field: 'avatar', sort: true, title: '头像'},
            {field: 'account', sort: true, title: '账号'},
            {field: 'password', sort: true, title: '密码'},
            // {field: 'salt', sort: true, title: 'md5密码盐'},
            {field: 'name', sort: true, title: '名字'},
            {field: 'birthday', sort: true, title: '生日'},
            {field: 'sex', sort: true, title: '性别(字典)'},
            {field: 'email', sort: true, title: '电子邮件'},
            {field: 'phone', sort: true, title: '电话'},
            // {field: 'roleId', sort: true, title: '角色id(多个逗号隔开)'},
            // {field: 'deptId', sort: true, title: '部门id(多个逗号隔开)'},
            // {field: 'status', sort: true, title: '状态(字典)'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'updateUser', sort: true, title: '更新人'},
            // {field: 'version', sort: true, title: '乐观锁'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    SysUser.search = function () {
        var queryData = {};

        queryData['account'] = $('#account').val();
        queryData['name'] = $('#name').val();
        queryData['phone'] = $('#phone').val();

        table.reload(SysUser.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    SysUser.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/sysUser/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    SysUser.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/sysUser/edit?userId=' + data.userId
    };

    /**
     * 导出excel按钮
     */
    SysUser.exportExcel = function () {
        var checkRows = table.checkStatus(SysUser.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    SysUser.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sysUser/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SysUser.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", data.userId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + SysUser.tableId,
        url: Feng.ctxPath + '/sysUser/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SysUser.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SysUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    SysUser.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        SysUser.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + SysUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SysUser.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            SysUser.onDeleteItem(data);
        }
    });
});
