package cn.stylefeng.guns.modular.position.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.position.entity.SysPosition;
import cn.stylefeng.guns.modular.position.model.params.SysPositionParam;
import cn.stylefeng.guns.modular.position.service.SysPositionService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 职位表控制器
 *
 * @author Hua
 * @Date 2020-09-15 17:07:23
 */
@Controller
@RequestMapping("/sysPosition")
public class SysPositionController extends BaseController {

    private String PREFIX = "/modular/sysPosition";

    @Autowired
    private SysPositionService sysPositionService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/sysPosition.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/sysPosition_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/sysPosition_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SysPositionParam sysPositionParam) {
        this.sysPositionService.add(sysPositionParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(SysPositionParam sysPositionParam) {
        this.sysPositionService.update(sysPositionParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(SysPositionParam sysPositionParam) {
        this.sysPositionService.delete(sysPositionParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(SysPositionParam sysPositionParam) {
        SysPosition detail = this.sysPositionService.getById(sysPositionParam.getPositionId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(SysPositionParam sysPositionParam) {
        return this.sysPositionService.findPageBySpec(sysPositionParam);
    }

}


