package cn.stylefeng.guns.workflow.modular.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.workflow.modular.controller.base.AcStartController;
import cn.stylefeng.guns.workflow.modular.entity.Myleave;
import cn.stylefeng.guns.workflow.modular.model.params.MyLeaveParam;
import cn.stylefeng.guns.workflow.modular.service.MyleaveService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ActivitiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum.ACT_NO_FLOW;


/**
 * 请假审批
 *
 * @author fengshuonan
 * @Date 2019-08-20 08:53:25
 */
@Controller
@RequestMapping("/myleave")
@Slf4j
public class MyLeaveController extends AcStartController {

    private String PREFIX = "/modular/act/myleave";

    @Autowired
    private MyleaveService myleaveService;

    /**
     * 跳转到主页面
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/myleave.html";
    }

    /**
     * 新增页面
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/myleave_add.html";
    }

    /**
     * 编辑页面
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/myleave_edit.html";
    }

    /**
     * 新增接口
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(MyLeaveParam myleaveParam) {

        String account = LoginContextHolder.getContext().getUser().getAccount();
        String username = LoginContextHolder.getContext().getUser().getName();

        try {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("请假人员", username);
            map.put("开始时间", myleaveParam.getStarttime());
            map.put("结束时间", myleaveParam.getEndtime());
            map.put("请假时长", myleaveParam.getWhenlong());
            map.put("请假类型", myleaveParam.getType());
            map.put("请假事由", myleaveParam.getReason());

            //指派代理人为当前用户
            map.put("username", account);

            //启动流程实例(请假单流程)通过KEY
            startProcessInstanceByKeyHasVariables("KEY_leave", map);

            myleaveParam.setUsername(account);
            this.myleaveService.add(myleaveParam);

        } catch (ActivitiException e) {
            log.error("无可用流程，请先导入或新建流程", e);
            throw new ServiceException(ACT_NO_FLOW);
        }

        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(MyLeaveParam myleaveParam) {
        this.myleaveService.update(myleaveParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(MyLeaveParam myleaveParam) {
        this.myleaveService.delete(myleaveParam);
        return ResponseData.success();
    }

    /**
     * 批量删除接口
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public ResponseData batchDeleteByIds(@RequestParam("ids") List<String> ids) {
        if (ToolUtil.isEmpty(ids)) {
            return ResponseData.success("无选中项");
        }
        this.myleaveService.batchDeleteByIds(ids);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(MyLeaveParam myleaveParam) {
        Myleave detail = this.myleaveService.getById(myleaveParam.getMyleaveId());
        return ResponseData.success(detail);
    }

    /**
     * 请假单查询列表
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(MyLeaveParam myleaveParam) {
        return this.myleaveService.findPageBySpec(myleaveParam);
    }

    /**
     * 获取请假类型
     *
     * @author fengshuonan
     * @Date 2019-08-20
     */
    @ResponseBody
    @RequestMapping("/getLeaves")
    public ResponseData getLeaves() {
        return ResponseData.success(myleaveService.getLeaves());
    }

}


