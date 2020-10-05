package cn.stylefeng.guns.workflow.modular.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.controller.base.ActBaseController;
import cn.stylefeng.guns.workflow.core.consts.ActConst;
import cn.stylefeng.guns.workflow.core.util.ImageAnd64Binary;
import cn.stylefeng.guns.workflow.core.util.PathUtil;
import cn.stylefeng.guns.workflow.core.util.TimeCalcUtil;
import cn.stylefeng.guns.workflow.modular.service.HistoryProcessService;
import cn.stylefeng.guns.workflow.modular.service.TaskHistoryService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 已办任务
 *
 * @author fengshuonan
 * @Date 2019-08-27 18:41
 */
@Controller
@RequestMapping("/taskHistory")
public class TaskHistoryController extends ActBaseController {

    private String PREFIX = "/modular/act/taskHistory";

    @Autowired
    private TaskHistoryService taskHistoryService;

    @Autowired
    private HistoryProcessService historyProcessService;

    /**
     * 跳转到主页面
     *
     * @author fengshuonan
     * @Date 2019-8-23 10:41
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/taskHistoryList.html";
    }

    /**
     * 已办任务列表
     *
     * @author fengshuonan
     * @Date 2019-8-23 15:15
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public LayuiPageInfo list(@RequestParam Map<String, Object> map) {
        return taskHistoryService.historyTaskListPage(map);
    }

    /**
     * 跳转到流程信息页面
     *
     * @author fengshuonan
     * @Date 2019-8-23 15:57
     */
    @RequestMapping("/processInfo")
    public String processInfo(@RequestParam("ID_") String id,
                              @RequestParam("DGRM_RESOURCE_NAME_") String dgrmResourceName,
                              @RequestParam("PROC_INST_ID_") String procInstId,
                              Model model) {

        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException("id不能为空");
        }
        model.addAttribute("ID_", id);
        model.addAttribute("DGRM_RESOURCE_NAME_", dgrmResourceName);
        model.addAttribute("PROC_INST_ID_", procInstId);
        return PREFIX + "/taskHistoryView.html";
    }

    /**
     * 查看流程信息
     *
     * @author fengshuonan
     * @Date 2019-8-23 15:19
     */
    @RequestMapping(value = "/view")
    @ResponseBody
    public Object view(@RequestParam("fileName") String fileName,
                       @RequestParam("procInstId") String procInstId) throws Exception {

        Map<String, Object> map = new HashMap<>();

        String errInfo = "success";

        //列出历史流程变量列表
        Map<String, Object> varList = historyProcessService.hivarList(procInstId);

        //历史任务节点列表
        List<Map<String, Object>> hitaskList = taskHistoryService.historyTaskList(procInstId);

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
