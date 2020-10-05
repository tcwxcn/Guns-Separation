package cn.stylefeng.guns.workflow.modular.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.controller.base.AcBusinessController;
import cn.stylefeng.guns.workflow.modular.entity.RuTask;
import cn.stylefeng.guns.workflow.modular.model.params.RuTaskParam;
import cn.stylefeng.guns.workflow.modular.service.TaskRunningService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 运行时流程
 *
 * @author stylefeng
 * @Date 2019-08-22 21:13:20
 */
@Controller
@RequestMapping("/taskRunning")
public class TaskRunningController extends AcBusinessController {

    private String PREFIX = "/modular/act/taskRunning";

    @Autowired
    private TaskRunningService ruTaskService;

    /**
     * 跳转到主页面
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/taskRunning.html";
    }

    /**
     * 删除接口
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(RuTaskParam ruTaskParam) {
        this.ruTaskService.delete(ruTaskParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(RuTaskParam ruTaskParam) {
        RuTask detail = this.ruTaskService.getById(ruTaskParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-08-22
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(RuTaskParam ruTaskParam) {

        if (ToolUtil.isNotEmpty(ruTaskParam.getLastStart())) {
            ruTaskParam.setLastStart(ruTaskParam.getLastStart() + " 00:00:00");
        }

        if (ToolUtil.isNotEmpty(ruTaskParam.getLastEnd())) {
            ruTaskParam.setLastEnd(ruTaskParam.getLastEnd() + " 23:59:59");
        }

        LayuiPageInfo pageBySpec = this.ruTaskService.findPageBySpec(ruTaskParam);

        List<Map<String, Object>> data = pageBySpec.getData();

        //流程申请人
        for (Map<String, Object> item : data) {
            item.put("initator", super.getInitiator((String) item.get("procInstId")));
        }

        return pageBySpec;
    }

}


