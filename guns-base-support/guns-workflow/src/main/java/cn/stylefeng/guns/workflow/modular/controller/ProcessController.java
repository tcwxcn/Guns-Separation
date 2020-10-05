package cn.stylefeng.guns.workflow.modular.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.util.FileDownload;
import cn.stylefeng.guns.workflow.modular.controller.base.ActBaseController;
import cn.stylefeng.guns.workflow.core.util.FileZip;
import cn.stylefeng.guns.workflow.core.util.PathUtil;
import cn.stylefeng.guns.workflow.modular.model.params.ProcdefParam;
import cn.stylefeng.guns.workflow.modular.service.ProcessService;
import cn.stylefeng.guns.sys.modular.system.model.UploadResult;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 流程管理控制器
 *
 * @author stylefeng
 * @Date 2019-08-11 21:59:38
 */
@Controller
@RequestMapping("/process")
@Slf4j
public class ProcessController extends ActBaseController {

    private String PREFIX = "/modular/act/process";

    @Autowired
    private ProcessService processService;

    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 跳转到主页面
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/process.html";
    }

    /**
     * 导入流程
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/process_add.html";
    }

    /**
     * 导入流程
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(@RequestPart("file") MultipartFile file) {
        if (null != file && !file.isEmpty()) {

            //bpmn文件上传路径
            String fileParentPath = ConstantsContext.getBpmnFileUploadPath();

            //执行上传
            UploadResult uploadResult = fileInfoService.uploadFile(file, fileParentPath);

            try {
                deploymentProcessDefinitionFromInputStream(uploadResult.getOriginalFilename(), uploadResult.getFileSavePath());
            } catch (Exception e) {
                log.error("上传文件出错", e);
                return ResponseData.error("上传文件出错");
            }
        }

        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(ProcdefParam procdefParam) {
        try {
            super.deleteDeployment(procdefParam.getDeploymentId());
        } catch (Exception e) {
            log.error("删除失败！", e);
            return ResponseData.error("删除失败！");
        }
        return ResponseData.success();
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-08-11
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(ProcdefParam procdefParam) {
        return this.processService.findPageBySpec(procdefParam);
    }

    /**
     * 打包下载xml和png
     *
     * @author fengshuonan
     * @Date 2019/8/12 21:37
     */
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response, String deploymentId) throws Exception {

        //生成XML和PNG
        createXmlAndPng(deploymentId);

        //生成的全部代码压缩成zip文件
        if (FileZip.zip(PathUtil.getProjectpath() + "uploadFiles/activitiFile",
                PathUtil.getProjectpath() + "uploadFiles/activitiFile.zip")) {

            //下载代码
            FileDownload.fileDownload(response, PathUtil.getProjectpath() + "uploadFiles/activitiFile.zip", "activitiFile.zip");
        }
    }

    /**
     * 激活or挂起流程实例
     *
     * @author fengshuonan
     * @Date 2019/8/12 21:41
     */
    @RequestMapping(value = "/onoffPro")
    @ResponseBody
    public ResponseData onoffProcessDefinition(@RequestParam("id") String id,
                                               @RequestParam("status") Integer status) throws Exception {

        //如果是挂起
        if (status == 2) {

            //挂起前先把此流程的所有任务状态设置成激活状态
            this.processService.onoffAllTask(id, 1);

            //挂起流程实例
            suspendProcessDefinitionById(id);
        } else {

            //激活前先把此流程的所有任务状态设置成挂起状态
            this.processService.onoffAllTask(id, 2);

            //激活流程实例
            activateProcessDefinitionById(id);
        }

        return new SuccessResponseData();
    }

    /**
     * 激活or挂起某个任务
     *
     * @author fengshuonan
     * @Date 2019/8/27 10:52
     */
    @RequestMapping(value = "/onoffTask")
    @ResponseBody
    public ResponseData onoffTask(@RequestParam("id") String id,
                                  @RequestParam("status") Integer status) {

        if (ToolUtil.isEmpty(id) || ToolUtil.isEmpty(status)) {
            throw new RequestEmptyException("参数不能为空");
        }
        this.processService.onoffTask(id, status);
        return ResponseData.success();
    }


    /**
     * 委派
     *
     * @author fengshuonan
     * @Date 2019/8/21 21:41
     */
    @RequestMapping(value = "/delegate")
    @ResponseBody
    public ResponseData delegate(@RequestParam("id") String id, @RequestParam("assignee") String assignee) {

        if (ToolUtil.isEmpty(id) || ToolUtil.isEmpty(assignee)) {
            throw new RequestEmptyException("id或assignee不能为空");
        }
        Map<String, Object> zmap = new HashMap<>();
        String errInfo = "success";
        Map<String, Object> map = new LinkedHashMap<>();

        //审批结果中记录委派
        map.put("审批结果", " 任务由 [" + LoginContextHolder.getContext().getUser().getAccount() + "] 委派  ");

        //设置流程变量
        setVariablesByTaskIdAsMap(id, map);
        setAssignee(id, assignee);

        //用于给待办人发送新任务消息
        zmap.put("ASSIGNEE_", assignee);

        //返回结果
        zmap.put("result", errInfo);

        return ResponseData.success(zmap);
    }

    /**
     * 作废流程
     *
     * @author fengshuonan
     * @Date 2019/8/21 21:41
     */
    @RequestMapping(value = "/deleteAct")
    @ResponseBody
    public ResponseData delete(@RequestParam("procInstId") String procInstId, @RequestParam("reason") String dReason) {

        if (ToolUtil.isEmpty(procInstId)) {
            throw new RequestEmptyException("procInstId不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        String errInfo = "success";

        //作废原因
        String reason = null;
        try {
            reason = "【作废】" + LoginContextHolder.getContext().getUser().getName() + "：" + URLDecoder.decode(dReason, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("作废失败，编码错误！", e);
        }

        //作废流程
        deleteProcessInstance(procInstId, reason);

        map.put("result", errInfo);

        return ResponseData.success(map);
    }

}


