layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 用户信息表管理
     */
    var ZjUserInfo = {
        tableId: "zjUserInfoTable"
    };

    /**
     * 初始化表格的列
     */
    ZjUserInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'userInfoId', hide: true, title: '主键id'},
            {field: 'userResumesId', sort: true, title: '用户简历id'},
            {field: 'posInfoId', sort: true, title: '职位信息id'},
            {field: 'account', sort: true, title: '账号'},
            {field: 'password', sort: true, title: '密码'},
            {field: 'salt', sort: true, title: 'md5密码盐'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'sex', sort: true, title: '性别'},
            {field: 'birthday', sort: true, title: '生日'},
            {field: 'email', sort: true, title: '电子邮件'},
            {field: 'phone', sort: true, title: '电话'},
            {field: 'status', sort: true, title: '状态(字典)'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '更新时间'},
            {field: 'createUser', sort: true, title: '创建人'},
            {field: 'updateUser', sort: true, title: '更新人'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    ZjUserInfo.search = function () {
        var queryData = {};

        queryData['userInfoId'] = $('#userInfoId').val();
        queryData['userResumesId'] = $('#userResumesId').val();
        queryData['posInfoId'] = $('#posInfoId').val();
        queryData['account'] = $('#account').val();
        queryData['phone'] = $('#phone').val();

        table.reload(ZjUserInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZjUserInfo.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zjUserInfo/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZjUserInfo.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zjUserInfo/edit?userInfoId=' + data.userInfoId
    };

    /**
     * 导出excel按钮
     */
    ZjUserInfo.exportExcel = function () {
        var checkRows = table.checkStatus(ZjUserInfo.tableId);
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
    ZjUserInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zjUserInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZjUserInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userInfoId", data.userInfoId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZjUserInfo.tableId,
        url: Feng.ctxPath + '/zjUserInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZjUserInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZjUserInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZjUserInfo.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZjUserInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZjUserInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZjUserInfo.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZjUserInfo.onDeleteItem(data);
        }
    });
});
