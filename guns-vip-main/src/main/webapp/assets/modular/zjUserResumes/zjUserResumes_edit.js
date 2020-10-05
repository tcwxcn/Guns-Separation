/**
 * 详情对话框
 */
var ZjUserResumesInfoDlg = {
    data: {
        resumesId: "",
        avatar: "",
        name: "",
        sex: "",
        age: "",
        height: "",
        weight: "",
        phone: "",
        email: "",
        education: "",
        currentAddress: "",
        permanentAddress: "",
        statusJob: "",
        arrivalTime: "",
        expectedMonthlySalary: "",
        expectedWorkplace: "",
        expectedPosition: "",
        remark: "",
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
    var ajax = new $ax(Feng.ctxPath + "/zjUserResumes/detail?resumesId=" + Feng.getUrlParam("resumesId"));
    var result = ajax.start();
    form.val('zjUserResumesForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/zjUserResumes/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/zjUserResumes'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/zjUserResumes'
    });
});