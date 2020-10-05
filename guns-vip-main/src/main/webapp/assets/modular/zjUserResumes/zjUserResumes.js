layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 个人简历信息表管理
     */
    var ZjUserResumes = {
        tableId: "zjUserResumesTable"
    };

    /**
     * 初始化表格的列
     */
    ZjUserResumes.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'resumesId', hide: true, title: '主键'},
            {field: 'avatar', sort: true, title: '头像'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'sex', sort: true, title: '性别'},
            {field: 'age', sort: true, title: '年龄'},
            {field: 'height', sort: true, title: '身高'},
            {field: 'weight', sort: true, title: '体重'},
            {field: 'phone', sort: true, title: '电话'},
            {field: 'email', sort: true, title: '电子邮件'},
            {field: 'education', sort: true, title: '最高学历'},
            {field: 'currentAddress', sort: true, title: '现居住地'},
            {field: 'permanentAddress', sort: true, title: '户口所在地'},
            {field: 'statusJob', sort: true, title: '求职状态'},
            {field: 'arrivalTime', sort: true, title: '到岗时间'},
            {field: 'expectedMonthlySalary', sort: true, title: '期望月薪'},
            {field: 'expectedWorkplace', sort: true, title: '期望工作地点'},
            {field: 'expectedPosition', sort: true, title: '期望职位'},
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
    ZjUserResumes.search = function () {
        var queryData = {};

        queryData['name'] = $('#name').val();
        queryData['phone'] = $('#phone').val();

        table.reload(ZjUserResumes.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    ZjUserResumes.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/zjUserResumes/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    ZjUserResumes.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/zjUserResumes/edit?resumesId=' + data.resumesId
    };

    /**
     * 导出excel按钮
     */
    ZjUserResumes.exportExcel = function () {
        var checkRows = table.checkStatus(ZjUserResumes.tableId);
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
    ZjUserResumes.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/zjUserResumes/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ZjUserResumes.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("resumesId", data.resumesId);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ZjUserResumes.tableId,
        url: Feng.ctxPath + '/zjUserResumes/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ZjUserResumes.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ZjUserResumes.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ZjUserResumes.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ZjUserResumes.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ZjUserResumes.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ZjUserResumes.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            ZjUserResumes.onDeleteItem(data);
        }
    });
});
