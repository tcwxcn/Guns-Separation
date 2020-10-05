package cn.stylefeng.guns.modular.user.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.user.entity.SysUser;
import cn.stylefeng.guns.modular.user.model.params.SysUserParam;
import cn.stylefeng.guns.modular.user.service.SysUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 管理员表控制器
 *
 * @author Hua
 * @Date 2020-09-15 10:20:36
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    private String PREFIX = "/modular/sysUser";

    @Autowired
    private SysUserService sysUserService;

    /**
     * 跳转到主页面
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/sysUser.html";
    }

    /**
     * 新增页面
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/sysUser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/sysUser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Hua
     * @Date 2020-09-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(SysUserParam sysUserParam) {
        this.sysUserService.add(sysUserParam);
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
    public ResponseData editItem(SysUserParam sysUserParam) {
        this.sysUserService.update(sysUserParam);
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
    public ResponseData delete(SysUserParam sysUserParam) {
        this.sysUserService.delete(sysUserParam);
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
    public ResponseData detail(SysUserParam sysUserParam) {
        SysUser detail = this.sysUserService.getById(sysUserParam.getUserId());
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
    public LayuiPageInfo list(SysUserParam sysUserParam) {
        return this.sysUserService.findPageBySpec(sysUserParam);
    }

}


