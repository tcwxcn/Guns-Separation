/**
 * 详情对话框
 */
var ZPosInfoInfoDlg = {
    data: {
        posInfoId: "",
        title: "",
        posName: "",
        content: "",
        request: "",
        date: "",
        time: "",
        location: "",
        demandNum: "",
        salary: "",
        settlementInterval: "",
        welfare: "",
        contact: "",
        notice: "",
        createTime: "",
        updateTime: "",
        createUser: "",
        updateUser: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;









































    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/zPosInfo/detail?posInfoId=" + Feng.getUrlParam("posInfoId"));
    var result = ajax.start();
    form.val('zPosInfoForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/zPosInfo/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/zPosInfo'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/zPosInfo'
    });
});