/**
 * 详情对话框
 */
var ZjPosInfoInfoDlg = {
    data: {
        posInfoId: "",
        title: "",
        job: "",
        num: "",
        time: "",
        location: "",
        content: "",
        request: "",
        salary: "",
        settlementInterval: "",
        contact: "",
        sort: "",
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
    var ajax = new $ax(Feng.ctxPath + "/zjPosInfo/detail?posInfoId=" + Feng.getUrlParam("posInfoId"));
    var result = ajax.start();
    form.val('zjPosInfoForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/zjPosInfo/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/zjPosInfo'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/zjPosInfo'
    });
});