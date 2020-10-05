layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    var ajax = new $ax(Feng.ctxPath + "/model/getXmlView?modelId=" + Feng.getUrlParam("modelId"));
    var result = ajax.start();

    $("#code").html(result.data);

    layui.use('code', function () {

        layui.code({
            encode: true,
            about: false
        });

    });
});