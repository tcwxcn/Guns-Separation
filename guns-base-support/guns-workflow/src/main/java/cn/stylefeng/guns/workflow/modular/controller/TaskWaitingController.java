package cn.stylefeng.guns.workflow.modular.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.controller.base.ActBaseController;
import cn.stylefeng.guns.workflow.core.cache.TempAssignCache;
import cn.stylefeng.guns.workflow.core.consts.ActConst;
import cn.stylefeng.guns.workflow.core.util.ImageAnd64Binary;
import cn.stylefeng.guns.workflow.core.util.PathUtil;
import cn.stylefeng.guns.workflow.core.util.TimeCalcUtil;
import cn.stylefeng.guns.workflow.modular.model.params.ActHandleDto;
import cn.stylefeng.guns.workflow.modular.model.params.TaskParam;
import cn.stylefeng.guns.workflow.modular.service.ProcessService;
import cn.stylefeng.guns.workflow.modular.service.TaskWaitingService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 待办任务控制器
 *
 * @author fengshuonan
 * @Date 2019-08-19 16:17:40
 */
@Controller
@RequestMapping("/taskWaiting")
@Slf4j
public class TaskWaitingController extends ActBaseController {

    private String PREFIX = "/modular/act/taskWaiting";

    @Autowired
    private TaskWaitingService taskWaitingService;

    @Autowired
    private ProcessService processService;

    /**
     * 跳转到主页面
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/taskWaiting.html";
    }

    /**
     * 委派页面
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    @RequestMapping("/delegatePage")
    public String delegatePage(@RequestParam("ID_") String id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException("id不能为空");
        }
        model.addAttribute("ID_", id);
        return PREFIX + "/delegate.html";
    }

    /**
     * 办理页面
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    @RequestMapping("/handlePage")
    public String handlePage(@RequestParam("ID_") String id,
                             @RequestParam("DGRM_RESOURCE_NAME_") String dgrmResourceName,
                             @RequestParam("PROC_INST_ID_") String procInstId,
                             Model model) {

        if (ToolUtil.isEmpty(id) || ToolUtil.isEmpty(procInstId)) {
            throw new RequestEmptyException("id不能为空");
        }

        model.addAttribute("ID_", id);
        model.addAttribute("DGRM_RESOURCE_NAME_", dgrmResourceName);
        model.addAttribute("PROC_INST_ID_", procInstId);

        return PREFIX + "/handle.html";
    }

    /**
     * 委派人员页面
     *
     * @author fengshuonan
     * @Date 2019-08-19
     */
    @RequestMapping("/delegateUserPage")
    public String delegateUserPage(@RequestParam("ID_") String id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException("id不能为空");
        }
        model.addAttribute("ID_", id);
        return PREFIX + "/user.html";
    }

    /**
     * 选择办理人
     *
     * @author fengshuonan
     * @Date 2019-8-22 19:01
     */
    @RequestMapping("/selectDealer")
    public String selectDealer() {
        return PREFIX + "/selectDealer.html";
    }

    /**
     * 选择角色
     *
     * @author fengshuonan
     * @Date 2019-8-22 19:01
     */
    @RequestMapping("/selectRole")
    public String selectRole() {
        return PREFIX + "/selectRole.html";
    }

    /**
     * 待办任务查询列表
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(TaskParam taskParam) {

        LayuiPageInfo pageBySpec = this.taskWaitingService.findPageBySpec(taskParam);

        //添加流程申请人
        if (ToolUtil.isNotEmpty(pageBySpec.getData())) {
            List<Map<String, Object>> maps = pageBySpec.getData();
            for (int i = 0; i < maps.size(); i++) {
                maps.get(i).put("initator", getInitiator((String) maps.get(i).get("proc_inst_id_")));
            }
        }

        return pageBySpec;
    }


    /**
     * 办理任务
     *
     * @author fengshuonan
     * @Date 2019-08-21
     */
    @RequestMapping(value = "/handle")
    @ResponseBody
    public ResponseData handle(ActHandleDto actHandleDto) {

        //任务ID
        String taskId = actHandleDto.getID_();

        //审批结果
        String sfrom = "";
        Object ofrom = getVariablesByTaskIdAsMap(taskId, "审批结果");
        if (null != ofrom) {
            sfrom = ofrom.toString();
        }

        Map<String, Object> map = new LinkedHashMap<>();

        //审批结果+审批人的姓名+审批意见
        String opinion = sfrom + LoginContextHolder.getContext().getUser().getName() + "," + actHandleDto.getOPINION();

        String msg = actHandleDto.getMsg();

        //批准
        if ("yes".equals(msg)) {

            //审批结果
            map.put("审批结果", "【批准】" + opinion);

            //设置流程变量
            setVariablesByTaskIdAsMap(taskId, map);
            setVariablesByTaskId(taskId, "RESULT", "批准");
            completeMyPersonalTask(taskId);
        } else {

            //驳回
            map.put("审批结果", "【驳回】" + opinion);

            //设置流程变量
            setVariablesByTaskIdAsMap(taskId, map);
            setVariablesByTaskId(taskId, "RESULT", "驳回");
            completeMyPersonalTask(taskId);
        }

        try {
            //移除流程变量(从正在运行中)
            removeVariablesByPROC_INST_ID_(actHandleDto.getPROC_INST_ID_(), "RESULT");
        } catch (Exception e) {
            log.error("移除流程变量错误，此流程变量在历史中", e);
        }

        try {
            //下一待办对象
            String assignee = actHandleDto.getASSIGNEE_();
            if (ToolUtil.isNotEmpty(assignee)) {

                //指定下一任务待办对象
                setAssignee(TempAssignCache.get(), assignee);
            }
        } catch (Exception e) {
            //手动指定下一待办人，才会触发此异常
            //任务结束不需要指定下一步办理人了,发送站内信通知任务发起人
            log.error("指定下一代办人错误", e);
        }

        return new SuccessResponseData();
    }

    /**
     * 去办理任务页面获取数据
     *
     * @author fengshuonan
     * @Date 2019-08-28 13:46
     */
    @RequestMapping(value = "/getHandleData")
    @ResponseBody
    public ResponseData getHandleData(@RequestParam("fileName") String fileName,
                                      @RequestParam("id") String id,
                                      @RequestParam("procInstId") String procInstId) throws Exception {

        if (ToolUtil.isEmpty(id) || ToolUtil.isEmpty(procInstId)) {
            throw new RequestEmptyException("id或procInstId不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        String errInfo = "success";

        //列出流程变量列表
        Map<String, Object> varList = processService.varList(procInstId);

        //历史任务节点列表
        List<Map<String, Object>> hitaskList = processService.hitoryTaskList(procInstId);

        //根据耗时的毫秒数计算天时分秒
        for (int i = 0; i < hitaskList.size(); i++) {
            if (null != hitaskList.get(i).get("duration_")) {
                Long ztime = Long.parseLong(hitaskList.get(i).get("duration_").toString());
                hitaskList.get(i).put("ztime", TimeCalcUtil.calc(ztime));
            }
        }

        String filename = URLDecoder.decode(fileName, "UTF-8");

        //生成当前任务节点的流程图片
        createXmlAndPngAtNowTask(procInstId, filename);
        String imgSrcPath = PathUtil.getProjectpath() + ActConst.FILEACTIVITI + filename;

        //解决图片src中文乱码，把图片转成base64格式显示
        map.put("imgSrc", "data:image/jpeg;base64," + ImageAnd64Binary.getImageStr(imgSrcPath));

        //流程变量列表
        map.put("varList", varList);

        //审批记录表格
        map.put("hitaskList", hitaskList);

        //返回结果
        map.put("result", errInfo);

        return ResponseData.success(map);
    }

}


