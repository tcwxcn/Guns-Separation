layui.use(['form', 'admin', 'ax', 'laydate', 'func'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var func = layui.func;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //加载请假类型
    func.initDictSelect("/myleave/getLeaves", "type", "dictName", "dictName");

    //渲染时间选择框
    laydate.render({
        elem: '#starttime'
        ,trigger: 'click'
    });
    laydate.render({
        elem: '#endtime'
        ,trigger: 'click'
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {

        var ajax = new $ax(Feng.ctxPath + "/myleave/addItem", function (data) {
            Feng.success("添加成功！");
            admin.putTempData('formOk', true);
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message);
        });
        ajax.set(data.field);
        ajax.start();

        return false;

    });

});