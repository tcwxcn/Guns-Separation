var RoleInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
};
layui.use(['ax','layer'], function () {
    var $ = layui.$;
    var $ax = layui.ax;
    var layer = layui.layer;

    var ID_ = $("#ID_").attr("value");

    /**
     * 指定人员
     */
    $("#btnSetUser").click(function () {
        layer.open({
            type: 2,
            title: '指定人员',
            area: ['600px', '400px'],
            content: Feng.ctxPath + '/taskWaiting/delegateUserPage?ID_='+ ID_,
            end: function () {
            }
        });
    });

    /**
     * 委派
     */
    $("#delegate").click(function () {
        var id = $("#ID_").attr("value");
        var assignee = $("#setValue").attr("value");

        if (assignee == null || assignee == "") {
            layer.msg("请先指定人员或角色");
            return;
        }
        var ajax = new $ax(Feng.ctxPath + "/process/delegate", function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);//关闭当前页
        }, function (data) {
            Feng.error("委派失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", id);
        ajax.set("assignee", assignee);
        ajax.start();
    });

    /**
     * 指定角色
     */
    $("#btnSetRole").click(function () {

        var formName = encodeURIComponent("parent.RoleInfoDlg.data.pName");
        var formId = encodeURIComponent("parent.RoleInfoDlg.data.pid");
        var treeUrl = encodeURIComponent("/role/roleTreeList");

        layer.open({
            type: 2,
            title: '指定角色',
            area: ['300px', '350px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#delegateObj").val(RoleInfoDlg.data.pName);
                $("#setValue").attr("value",RoleInfoDlg.data.pid);
            }
        });

    });


});