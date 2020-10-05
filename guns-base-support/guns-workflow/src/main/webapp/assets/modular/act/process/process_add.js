/**
 * 添加或者修改页面
 */
var ProcdefInfoDlg = {
    data: {
        rev: "",
        category: "",
        name: "",
        key: "",
        version: "",
        deploymentId: "",
        resourceName: "",
        dgrmResourceName: "",
        description: "",
        hasStartFormKey: "",
        hasGraphicalNotation: "",
        suspensionState: "",
        tenantId: "",
        engineVersion: ""
    }
};

layui.use(['form', 'admin', 'ax', 'upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;

    //上传文件
    upload.render({
        elem: '#fileBtn'
        , url: Feng.ctxPath + '/process/addItem'
        , accept: 'file'
        , auto: false
        , bindAction: '#submit'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $("#fileNameTip").html(file.name);
            });
        }
        , done: function (res) {
            Feng.success(res.message);
            admin.putTempData('formOk', true);
            admin.closeThisDialog();
        }
        , error: function () {
            Feng.error("上传图片失败！");
        }
    });

});